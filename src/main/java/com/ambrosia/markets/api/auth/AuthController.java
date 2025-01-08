package com.ambrosia.markets.api.auth;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.system.ApiGson;
import com.ambrosia.markets.api.system.AppStatusCode;
import com.ambrosia.markets.api.upstream.DiscordRateLimit;
import com.ambrosia.markets.api.upstream.authorize.UpstreamAuthorizeRequest;
import com.ambrosia.markets.api.upstream.authorize.UpstreamAuthorizeResponse;
import io.javalin.http.Context;
import java.util.concurrent.CompletableFuture;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

public class AuthController extends BaseController {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        TokenRequest request = ctx.bodyValidator(TokenRequest.class).get();
        validate(ctx, TokenRequest.VALIDATOR, request);

        CompletableFuture<?> future = new CompletableFuture<>();
        ctx.future(() -> future);

        Request verifyRequest = UpstreamAuthorizeRequest.create(request.getCode()).asRequest();
        DiscordRateLimit.makeTokenRequest(verifyRequest).whenCompleteAsync(
            (res, err) -> {
                if (err != null) {
                    ctx.status(AppStatusCode.SERVER_UNKNOWN);
                    future.complete(null);
                    return;
                }
                if (!res.isSuccessful()) {
                    ctx.status(res.code());
                    future.complete(null);
                    return;
                }

                ResponseBody body = res.body();
                UpstreamAuthorizeResponse response = ApiGson.fromJson(body, UpstreamAuthorizeResponse.class);
                ctx.json(response);
                ctx.status(200);
                future.complete(null);
            }
        );
    }
}
