package com.ambrosia.markets.api.base.client;

import com.ambrosia.markets.api.base.BaseRequest;
import com.ambrosia.markets.database.model.entity.client.ClientApi.ClientQueryApi;
import com.ambrosia.markets.database.model.entity.client.DClient;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;

public abstract class BaseClientAuthorizationRequest extends BaseRequest {

    private final DClient client;

    public BaseClientAuthorizationRequest(Context ctx) {
        super(ctx);
        this.client = clientAuthorization(ctx);
    }

    // todo use an actual token instead of clientName
    public static DClient clientAuthorization(Context ctx) {
        String authorization = ctx.header("Authorization");
        if (authorization == null) throw new UnauthorizedResponse("No authorization header");
        String BEARER = "Bearer ";
        if (!authorization.startsWith(BEARER)) throw new UnauthorizedResponse("Bearer token not found");
        String tokenBase64 = authorization.substring(BEARER.length());
        DClient client = ClientQueryApi.findByName(tokenBase64);
        if (client == null) throw new UnauthorizedResponse("Unknown client.");
        return client;
    }

    public DClient getClient() {
        return client;
    }
}
