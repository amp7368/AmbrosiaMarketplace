package com.ambrosia.markets.api.dto.user;

import com.ambrosia.markets.database.model.entity.client.DClient;

public class PrivateUserResponse extends PublicUserResponse {

    public PrivateUserResponse(DClient client) {
        super(client);
    }
}
