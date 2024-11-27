package com.ambrosia.markets.database.model.entity.client.name;

import io.ebean.Transaction;
import io.ebean.annotation.DbEnumValue;
import java.util.Map;

public enum NameHistoryType {
    DISCORD_USER,
    MINECRAFT,
    DISPLAY_NAME;


    public DClientNameHistory createFromClient(DClientNameMeta client) {
        Object jsonObj = null;
        String name = null;
        switch (this) {
            case DISCORD_USER -> {
                ClientDiscordDetails discord = client.getDiscord(false);
                if (discord == null) break;
                jsonObj = discord.json();
                name = discord.getUsername();
            }
            case MINECRAFT -> {
                ClientMinecraftDetails minecraft = client.getMinecraft();
                if (minecraft == null) break;
                jsonObj = minecraft.json();
                name = minecraft.getUsername();
            }
            case DISPLAY_NAME -> {
                name = client.getDisplayName();
                jsonObj = Map.of("displayName", name);
            }
        }
        return new DClientNameHistory(client, this, name, jsonObj);
    }

    public DClientNameHistory updateName(DClientNameMeta client, DClientNameHistory lastName, Transaction transaction) {
        DClientNameHistory newName = createFromClient(client);
        lastName.retireName(newName.getFirstUsed());
        lastName.save(transaction);
        newName.save(transaction);
        return newName;
    }

    @DbEnumValue
    public String id() {
        return this.name();
    }

    @Override
    public String toString() {
        return switch (this) {
            case DISCORD_USER -> "Discord";
            case MINECRAFT -> "Minecraft";
            case DISPLAY_NAME -> "Display";
        };
    }
}
