package com.ambrosia.markets.database.model.entity.client.name;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.actor.UserActor;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.entity.client.name.update.UpdateClientMetaHook;
import com.ambrosia.markets.discord.system.log.DiscordLog;
import io.ebean.annotation.Cache;
import io.ebean.annotation.Index;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Cache(enableQueryCache = true, naturalKey = {"discord.id"})
@Entity
@Table(name = "client_name")
public class DClientNameMeta extends BaseEntity {

    @Id
    protected UUID id;

    @JoinColumn
    @OneToOne
    protected DClient client;
    @NotNull
    @Embedded(prefix = "minecraft_")
    protected ClientMinecraftDetails minecraft;
    @NotNull
    @Embedded(prefix = "discord_")
    protected ClientDiscordDetails discord;
    @Index
    @Column(unique = true)
    protected String displayName;
    @OneToMany
    protected List<DClientNameHistory> nameHistory = new ArrayList<>();

    public DClientNameMeta(DClient client, @NotNull ClientDiscordDetails discord, @NotNull ClientMinecraftDetails minecraft,
        String displayName) {
        this.client = client;
        this.discord = discord;
        this.minecraft = minecraft;
        this.displayName = displayName;
    }

    @NotNull
    public ClientMinecraftDetails getMinecraft() {
        return minecraft;
    }

    public DClientNameMeta setMinecraft(ClientMinecraftDetails minecraft) {
        this.minecraft.setAll(minecraft);
        return this;
    }

    @NotNull
    public ClientDiscordDetails getDiscord(boolean shouldUpdate) {
        if (shouldUpdate) UpdateClientMetaHook.hookUpdate(this);
        return this.discord;
    }

    public String getEffectiveName() {
        if (getDisplayName() != null) return getDisplayName();
        String minecraft = getMinecraft(ClientMinecraftDetails::getUsername);
        if (minecraft != null) return minecraft;
        String discord = getDiscord(ClientDiscordDetails::getUsername);
        if (discord != null) return discord;
        return "Not Found!";
    }

    @Nullable
    public <T> T getMinecraft(Function<ClientMinecraftDetails, T> apply) {
        ClientMinecraftDetails minecraft = getMinecraft();
        return apply.apply(minecraft);
    }

    @Nullable
    public <T> T getDiscord(Function<ClientDiscordDetails, T> apply) {
        ClientDiscordDetails discord = getDiscord(true);
        if (discord == null) return null;
        return apply.apply(discord);
    }

    public DClientNameHistory getNameNow(NameHistoryType nameType) {
        List<DClientNameHistory> history = getNameHistory(nameType);
        if (!history.isEmpty()) {
            DClientNameHistory now = history.getFirst();
            if (!now.isCurrent()) {
                String msg = "First entry in name history is not currently in use!";
                DiscordLog.error(msg, UserActor.of(getClient()));
            }
            return now;
        }
        DClientNameHistory firstName = nameType.createFromClient(this);
        firstName.save();
        this.refresh();
        return firstName;
    }

    public List<DClientNameHistory> getNameHistory(@Nullable NameHistoryType nameType) {
        Stream<DClientNameHistory> stream = this.nameHistory.stream();
        if (nameType != null) stream = stream.filter(n -> n.getType() == nameType);

        return stream
            .sorted(Comparator.comparing(DClientNameHistory::getFirstUsed).reversed())
            .toList();
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public DClientNameMeta setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public UUID getId() {
        return getClient().getId();
    }

    public ClientDiscordDetails getDiscord() {
        return this.getDiscord(true);
    }

    public DClientNameMeta setDiscord(ClientDiscordDetails discord) {
        this.discord.setAll(discord);
        return this;
    }

    @Nullable
    public String getDiscordAvatar() {
        return getDiscord(ClientDiscordDetails::getAvatarUrl);
    }

    public DClient getClient() {
        return this.client;
    }

}
