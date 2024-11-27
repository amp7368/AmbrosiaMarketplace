package com.ambrosia.markets.database.model.entity.client.name.update;

import com.ambrosia.markets.Ambrosia;
import com.ambrosia.markets.config.discord.DiscordConfig;
import com.ambrosia.markets.database.DatabaseModule;
import com.ambrosia.markets.database.model.entity.client.name.ClientDiscordDetails;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameHistory;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameMeta;
import com.ambrosia.markets.database.model.entity.client.name.NameHistoryType;
import com.ambrosia.markets.discord.DiscordBot;
import com.ambrosia.markets.discord.DiscordModule;
import com.ambrosia.markets.discord.system.log.DiscordLog;
import io.ebean.DB;
import io.ebean.Transaction;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public class UpdateClientDiscordHook {

    private static final Duration HOURS_TILL_UPDATE = Duration.ofHours(1);

    public static Future<Void> discordUpdate(DClientNameMeta clientName) {
        ClientDiscordDetails discord = clientName.getDiscord(false);
        if (discord == null) return null;
        Long discordId = discord.getDiscordId();
        if (discordId == null) return null;

        Duration between = Duration.between(discord.getLastUpdated(), Instant.now());
        if (between.compareTo(HOURS_TILL_UPDATE) < 0) return null;

        DatabaseModule.get().logger().info("Updating discord {}{{}}", discord.getUsername(), discordId);

        CompletableFuture<Void> task = new CompletableFuture<>();
        Member cachedMember = DiscordConfig.getMainServer().getMemberById(discordId);
        if (cachedMember != null) {
            updateAmbrosiaMember(clientName, cachedMember, task);
            return task;
        }

        DiscordConfig.getMainServer().retrieveMemberById(discordId)
            .queue(member -> updateAmbrosiaMember(clientName, member, task),
                fail -> updateAmbrosiaMemberFailed(clientName, discordId, task));
        return task;
    }

    private static void updateAmbrosiaMember(DClientNameMeta client, Member cachedMember, CompletableFuture<Void> task) {
        Ambrosia.get().submit(() -> {
            ClientDiscordDetails disc = ClientDiscordDetails.fromMember(cachedMember);
            updateDiscord(client, disc, task);
        });
    }

    private static void updateAmbrosiaMemberFailed(DClientNameMeta client, long discordId, CompletableFuture<Void> task) {
        DiscordBot.jda().retrieveUserById(discordId).queue(
            user -> updateFromUser(client, user, task),
            err -> retrieveUserFailed(client, discordId, task));
    }

    private static void retrieveUserFailed(DClientNameMeta client, long discordId, CompletableFuture<Void> task) {
        try {
            DiscordModule.get().logger().error("Could not update discord for: client {} discord{{}}",
                client.getEffectiveName(), discordId);
            ClientDiscordDetails discord = client.getDiscord(false);
            client.setDiscord(discord.updated());
            client.save();
        } catch (Exception e) {
            Ambrosia.get().logger().error("", e);
            DiscordLog.errorSystem("Cannot save Discord");
        } finally {
            task.complete(null);
        }
    }

    private static void updateFromUser(DClientNameMeta client, User user, CompletableFuture<Void> task) {
        Ambrosia.get().submit(() -> {
            ClientDiscordDetails disc = ClientDiscordDetails.fromUser(user);
            updateDiscord(client, disc, task);
        });
    }

    private static void updateDiscord(DClientNameMeta client, ClientDiscordDetails disc, CompletableFuture<Void> task) {
        try (Transaction transaction = DB.beginTransaction()) {
            client.refresh();
            boolean isNewName = client.getDiscord(false).isNewName(disc);
            if (isNewName) {
                DClientNameHistory lastName = client.getNameNow(NameHistoryType.DISCORD_USER);
                client.setDiscord(disc);
                DClientNameHistory newName = NameHistoryType.DISCORD_USER.updateName(client, lastName, transaction);
                DiscordLog.updateName(lastName, newName);
            }
            client.setDiscord(disc);
            client.save(transaction);
            transaction.commit();
            client.refresh();
        } catch (Exception e) {
            Ambrosia.get().logger().error("", e);
            DiscordLog.errorSystem("Cannot save Discord");
        } finally {
            task.complete(null);
        }
    }
}
