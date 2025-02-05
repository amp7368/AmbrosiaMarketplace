package com.ambrosia.markets.api.base;

import io.javalin.http.Context;
import java.time.Instant;

public class BaseRequest {

    private final Context ctx;
    private final Instant createdAt;

    public BaseRequest(Context ctx) {
        this.ctx = ctx;
        this.createdAt = Instant.now();
    }
}
