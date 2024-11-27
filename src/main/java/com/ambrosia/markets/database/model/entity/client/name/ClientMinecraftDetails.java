package com.ambrosia.markets.database.model.entity.client.name;

import com.ambrosia.markets.database.model.entity.client.name.update.UpdateClientMinecraftHook;
import io.ebean.annotation.Index;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

@Embeddable
public class ClientMinecraftDetails {

    @Index
    @Column(unique = true)
    private UUID uuid;
    @Index
    @Column
    private String username;
    @Column(nullable = false)
    private Instant lastUpdated;

    private ClientMinecraftDetails(UUID uuid, String username) {
        this.uuid = uuid;
        this.username = username;
        this.lastUpdated = Instant.now();
    }

    @Nullable
    public static ClientMinecraftDetails fromUsername(String username) {
        if (username == null) return null;

        return UpdateClientMinecraftHook.fromUsernameNow(username);
    }

    public static ClientMinecraftDetails fromRaw(UUID uuid, String username) {
        return new ClientMinecraftDetails(uuid, username);
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public String skinUrl() {
        return "https://mc-heads.net/head/" + this.uuid;
    }

    @Nullable
    public String getUsername() {
        return this.username;
    }

    @Nullable
    public UUID getUUID() {
        return uuid;
    }

    public boolean isNewName(ClientMinecraftDetails other) {
        if (!Objects.equals(this.uuid, other.uuid)) return true;
        return !Objects.equals(this.username, other.username);
    }

    public ClientMinecraftDetails updated() {
        return new ClientMinecraftDetails(this.uuid, this.username);
    }

    public Object json() {
        return Map.of(
            "uuid", uuid,
            "username", username
        );
    }

    public void setAll(ClientMinecraftDetails minecraft) {
        this.uuid = minecraft.uuid;
        this.username = minecraft.username;
        this.lastUpdated = minecraft.lastUpdated;
    }
}
