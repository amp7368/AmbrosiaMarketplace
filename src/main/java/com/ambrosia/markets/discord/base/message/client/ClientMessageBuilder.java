package com.ambrosia.markets.discord.base.message.client;

import com.ambrosia.markets.database.model.entity.client.DClient;

public class ClientMessageBuilder implements ClientMessage {

    private final DClient client;

    ClientMessageBuilder(DClient client) {
        this.client = client;
    }

    @Override
    public DClient getClient() {
        return client;
    }
}
