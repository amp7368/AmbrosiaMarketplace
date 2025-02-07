package com.ambrosia.markets.api.v1.controller.users.items;

import com.ambrosia.markets.api.base.BaseRequest;
import com.ambrosia.markets.api.base.client.ClientParams;
import com.ambrosia.markets.database.model.entity.client.DClient;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import org.jetbrains.annotations.NotNull;

public class UsersItemsRequest extends BaseRequest {

    private final DClient client;

    public UsersItemsRequest(@NotNull Context ctx, String clientInput) throws HttpResponseException {
        super(ctx);
        client = ClientParams.findClientOrThrow(clientInput);
    }

    public DClient getClient() {
        return client;
    }
}
