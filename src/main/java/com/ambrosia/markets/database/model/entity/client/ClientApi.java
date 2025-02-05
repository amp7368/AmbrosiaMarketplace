package com.ambrosia.markets.database.model.entity.client;

import com.ambrosia.markets.database.model.entity.client.name.ClientDiscordDetails;
import com.ambrosia.markets.database.model.entity.client.name.ClientMinecraftDetails;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameMeta;
import com.ambrosia.markets.database.model.entity.client.name.query.QDClientNameMeta;
import com.ambrosia.markets.database.model.entity.client.query.QDClient;
import com.ambrosia.markets.database.model.profile.auction.DClientAuction;
import com.ambrosia.markets.database.model.profile.backpack.DClientBackpack;
import com.ambrosia.markets.database.system.exception.CreateEntityException;
import io.ebean.CacheMode;
import io.ebean.DB;
import io.ebean.DuplicateKeyException;
import io.ebean.Transaction;
import java.util.List;
import java.util.UUID;
import net.dv8tion.jda.api.entities.Member;
import org.jetbrains.annotations.Nullable;

public interface ClientApi {

    interface ClientQueryApi {

        @Nullable
        static DClient findByName(String clientName) {
            DClient client = new QDClient().where()
                .nameMeta.displayName.ieq(clientName)
                .findOne();
            if (client != null) return client;

            client = new QDClient().where()
                .nameMeta.minecraft.username.ieq(clientName)
                .findOne();
            if (client != null) return client;

            client = new QDClient().where()
                .nameMeta.discord.username.ieq(clientName)
                .findOne();

            return client;
        }

        static DClient findByDiscord(long discordId) {
            return new QDClient().where()
                .nameMeta.discord.id.eq(discordId)
                .setUseCache(true)
                .setBeanCacheMode(CacheMode.ON)
                .setReadOnly(false)
                .findOne();
        }

        static DClient findById(UUID id) {
            return new QDClient().where()
                .id.eq(id)
                .findOne();
        }

        static List<DClient> findAllReadOnly() {
            return new QDClient()
                .setUseQueryCache(true)
                .setReadOnly(true)
                .findList();
        }

        static List<DClientNameMeta> findAllNameMetaReadOnly() {
            return new QDClientNameMeta()
                .setUseQueryCache(true)
                .setReadOnly(true)
                .findList();
        }
    }

    interface ClientCreateApi {

        static DClient createClient(String clientName, String minecraftName, Member discordMember) throws CreateEntityException {
            if (ClientQueryApi.findByDiscord(discordMember.getIdLong()) != null) {
                throw new CreateEntityException("Your discord is already registered!");
            }
            if (ClientQueryApi.findByName(minecraftName) != null) {
                throw new CreateEntityException(
                    "That account already exists! If this is your account, it may just need to be linked to your discord");
            }

            @Nullable ClientMinecraftDetails minecraft = ClientMinecraftDetails.fromUsername(minecraftName);
            ClientDiscordDetails discord = ClientDiscordDetails.fromMember(discordMember);
            if (minecraft == null)
                throw new CreateEntityException("'%s' is not a valid minecraft username".formatted(minecraftName));

            DClient client = new DClient();
            DClientNameMeta nameMeta = new DClientNameMeta(client, discord, minecraft, clientName);
            DClientBackpack backpack = new DClientBackpack(client);
            DClientAuction auction = new DClientAuction(client);

            try (Transaction transaction = DB.beginTransaction()) {
                client.init(nameMeta, backpack, auction);

                client.save(transaction);
                nameMeta.save(transaction);
                backpack.save(transaction);
                auction.save(transaction);
                transaction.commit();
            } catch (DuplicateKeyException e) {
                throw new CreateEntityException(
                    "That account already exists! If this is your account, it may just need to be linked to your discord");
            }
            return client;
        }
    }
}
