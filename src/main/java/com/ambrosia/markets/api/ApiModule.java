package com.ambrosia.markets.api;

import apple.lib.modules.AppleModule;
import com.ambrosia.markets.api.auth.AuthController;
import com.ambrosia.markets.api.system.ApiGson;
import com.ambrosia.markets.api.system.ApiRequestLogger;
import com.ambrosia.markets.api.system.ExceptionHandlers;
import com.ambrosia.markets.config.AmbrosiaConfig;
import io.javalin.Javalin;
import io.javalin.json.JavalinGson;

public class ApiModule extends AppleModule {

    private static ApiModule instance;

    public ApiModule() {
        instance = this;
    }

    public static ApiModule get() {
        return instance;
    }

    private void registerControllers(Javalin app) {
        app.post("/api/token", new AuthController());
    }

//    private void registerPermissions() {
//        Arrays.stream(DefaultAuthPermission.values()).forEach(DefaultAuthPermission::get);
//    }

    private int getPort() {
        return AmbrosiaConfig.getApi().getPort();
    }

    @Override
    public String getName() {
        return "Api";
    }

    @Override
    public void onEnable() {
        Javalin app = Javalin.create(cfg -> {
            AmbrosiaConfig.getApi().commonConfig(cfg);
            cfg.requestLogger.http(new ApiRequestLogger());
            cfg.jsonMapper(new JavalinGson(ApiGson.gson(), false));
        });
        app.ws("/", wss -> {
            wss.onMessage(System.out::println);
        });
//        app.beforeMatched(new ApiSecurity()::manage);

        app.beforeMatched(ctx -> {
            System.out.println(ctx.path());
        });
//        registerPermissions();
        registerControllers(app);
        ExceptionHandlers.registerExceptions(app);

        app.start(getPort());
    }
}
