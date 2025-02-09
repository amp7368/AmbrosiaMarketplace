package com.ambrosia.markets.api.base.client;

import com.ambrosia.markets.api.base.BaseRequest;
import com.ambrosia.markets.database.model.entity.client.DClient;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import org.jetbrains.annotations.NotNull;

public class BaseClientRequest extends BaseRequest implements IClientRequest {

    private final DClient client;

    public BaseClientRequest(@NotNull Context ctx, String clientInput) throws HttpResponseException {
        super(ctx);
        client = ClientParams.findClientOrThrow(clientInput);
    }

    @Override
    public DClient getClient() {
        return client;
    }
}
