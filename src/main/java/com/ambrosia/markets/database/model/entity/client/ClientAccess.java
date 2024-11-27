package com.ambrosia.markets.database.model.entity.client;

import com.ambrosia.markets.database.model.entity.client.name.ClientDiscordDetails;
import net.dv8tion.jda.api.entities.User;

public interface ClientAccess {

    DClient getEntity();


    default boolean isUser(User user) {
        Long discord = getEntity().getNameMeta().getDiscord(ClientDiscordDetails::getDiscordId);
        return discord != null && discord == user.getIdLong();
    }
}
