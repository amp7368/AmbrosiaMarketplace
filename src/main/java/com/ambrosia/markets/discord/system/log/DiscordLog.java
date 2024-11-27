package com.ambrosia.markets.discord.system.log;

import com.ambrosia.markets.Ambrosia;
import com.ambrosia.markets.database.model.entity.actor.UserActor;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.entity.client.name.ClientDiscordDetails;
import com.ambrosia.markets.database.model.entity.client.name.ClientMinecraftDetails;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameHistory;
import com.ambrosia.markets.database.model.entity.client.name.NameHistoryType;
import com.ambrosia.markets.database.model.entity.staff.SystemConductor;
import com.ambrosia.markets.util.theme.AmbrosiaAssets.AmbrosiaEmoji;
import com.ambrosia.markets.util.theme.AmbrosiaMessages;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.local.LocalBucket;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import net.dv8tion.jda.api.entities.User;

public interface DiscordLog {

    LocalBucket ERROR_RATE_LIMIT = Bucket.builder()
        .addLimit(limit -> limit.capacity(20)
            .refillIntervally(10, Duration.ofHours(3))
            .initialTokens(20))
        .build();

    static UserActor actor(User actor) {
        return UserActor.of(actor);
    }

    private static DiscordLogService account(DClient client, UserActor actor, String type, String message) {
        return new DiscordLogService(client, actor, "Account", type, message);
    }

    static void modifyDiscord(DClient client, UserActor actor) {
        futureLog(() -> modifyDiscord_(client, actor));
    }

    private static DiscordLogService modifyDiscord_(DClient client, UserActor actor) {
        String type = "Modify Discord";
        String message = "Set Discord to @{discord}";
        String discord = client.getNameMeta().getDiscord(ClientDiscordDetails::getUsername);
        return account(client, actor, type, message)
            .addJson("discord", discord);
    }


    static void modifyMinecraft(DClient client, UserActor actor) {
        futureLog(() -> modifyMinecraft_(client, actor));
    }

    private static DiscordLogService modifyMinecraft_(DClient client, UserActor actor) {
        String type = "Modify Discord";
        String message = "Set Minecraft to @{minecraft}";
        String minecraft = client.getNameMeta().getMinecraft(ClientMinecraftDetails::getUsername);
        return account(client, actor, type, message)
            .addJson("minecraft", minecraft);
    }

    static void createAccount(DClient client, UserActor actor) {
        futureLog(() -> createAccount_(client, actor));
    }

    private static DiscordLogService createAccount_(DClient client, UserActor actor) {
        String type = "Create Account";
        String message = "Account was created";
        return account(client, actor, type, message);
    }

    static void updateAccount(DClient client, UserActor actor) {
        futureLog(() -> updateAccount_(client, actor));
    }

    private static DiscordLogService updateAccount_(DClient client, UserActor actor) {
        String type = "Update Account";
        String message = "Account was updated";
        return account(client, actor, type, message);
    }


    static void updateName(DClientNameHistory lastName, DClientNameHistory newName) {
        futureLog(() -> updateName_(lastName, newName));
    }

    private static DiscordLogService updateName_(DClientNameHistory lastName, DClientNameHistory newName) {
        DClient client = lastName.getClient();
        UserActor actor = SystemConductor.SYSTEM.actor();
        String category = "Name History";
        NameHistoryType nameHistoryType = lastName.getType();
        String logType = nameHistoryType.toString();
        String msg = """
            %s username updated
            %s **%s** => **%s**
            %s""".formatted(logType, AmbrosiaEmoji.CLIENT_ACCOUNT, lastName.getName(), newName.getName(),
            AmbrosiaMessages.formatDate(newName.getFirstUsed(), true));
        return new DiscordLogService(client, actor, category, logType, msg);
    }

    private static CompletableFuture<DiscordLogService> futureLog(Supplier<DiscordLogService> createLog) {
        CompletableFuture<DiscordLogService> future = new CompletableFuture<>();
        Ambrosia.get().execute(() -> {
            try {
                DiscordLogService log = createLog.get();
                future.complete(log.submit().get());
            } catch (InterruptedException | ExecutionException e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    static void infoSystem(String msg) {
        futureLog(() -> infoSystem_(msg));
    }

    private static DiscordLogService infoSystem_(String msg) {
        return new DiscordLogService(null, UserActor.system(), "System", "Info", msg);
    }

    static void errorSystem(String msg) {
        error(msg, SystemConductor.SYSTEM.actor());
    }

    static void error(String msg, UserActor actor) {
        boolean test = ERROR_RATE_LIMIT.tryConsume(1);
        if (!test) {
            Ambrosia.get().logger().error(msg);
            return;
        }
        futureLog(() -> error_(msg, actor));
    }

    private static DiscordLogService error_(String msg, UserActor of) {
        return new DiscordLogService(null, of, "System", "Error", msg);
    }
}
