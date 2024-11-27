package com.ambrosia.markets;

import apple.lib.modules.AppleModule;
import apple.lib.modules.ApplePlugin;
import apple.lib.modules.configs.factory.AppleConfigLike;
import com.ambrosia.markets.config.AmbrosiaConfig;
import com.ambrosia.markets.config.AmbrosiaStaffConfig;
import com.ambrosia.markets.database.DatabaseModule;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

public class Ambrosia extends ApplePlugin {

    private static Ambrosia instance;

    public Ambrosia() {
        instance = this;
    }

    public static void main(String[] args) {
        new Ambrosia().start();
    }

    public static Ambrosia get() {
        return instance;
    }

    public <T> void futureComplete(CompletableFuture<T> future, T value) {
        execute(() -> future.complete(value));
    }

    public <T> void futureException(CompletableFuture<T> future, Throwable e) {
        execute(() -> future.completeExceptionally(e));
    }

    public <T> Consumer<T> futureComplete(CompletableFuture<T> sent) {
        return obj -> futureComplete(sent, obj);
    }

    public <E extends Throwable, T> Consumer<E> futureException(CompletableFuture<T> sent) {
        return obj -> futureException(sent, obj);
    }

    @Override
    protected ScheduledExecutorService makeExecutor() {
        return Executors.newScheduledThreadPool(5);
    }

    @Override
    public String getName() {
        return "AmbrosiaMarkets";
    }

    @Override
    public List<AppleModule> createModules() {
        return List.of(new DatabaseModule());
    }

    @Override
    public List<AppleConfigLike> getConfigs() {
        return List.of(
            configJson(AmbrosiaConfig.class, "AmbrosiaConfig").setPretty(),
            configJson(AmbrosiaStaffConfig.class, "AmbrosiaStaffConfig").setPretty()
        );
    }
}
