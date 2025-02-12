package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.api.base.client.IClientRequest;
import com.ambrosia.markets.api.dto.user.PrivateUserResponse;
import com.ambrosia.markets.api.dto.user.PublicUserResponse;
import com.ambrosia.markets.database.model.entity.client.DClient;

public class ProfileService {

    public static PublicUserResponse viewPublicProfile(IClientRequest request) {
        DClient client = request.getClient();

        return new PublicUserResponse(client);
    }

    public static PublicUserResponse viewPrivateProfile(IClientRequest request) {
        DClient client = request.getClient();

        return new PrivateUserResponse(client);
    }
}
