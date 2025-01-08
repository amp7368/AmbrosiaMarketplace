package com.ambrosia.markets.api.upstream.authorize;

import com.ambrosia.markets.config.discord.DiscordConfig;
import com.ambrosia.markets.discord.DiscordBot;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UpstreamAuthorizeRequest {

    public String clientId;
    public String clientSecret;
    public String grantType;
    public String code;

    public UpstreamAuthorizeRequest(String clientId, String clientSecret, String grantType, String code) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
        this.code = code;
    }

    public static UpstreamAuthorizeRequest create(String code) {
        String clientId = DiscordBot.getApplicationId();
        String token = DiscordConfig.get().getApplicationToken();
        String grantType = "authorization_code";
        return new UpstreamAuthorizeRequest(clientId, token, grantType, code);
    }

    public Request asRequest() {
        return new Request.Builder()
            .url("https://discord.com/api/oauth2/token")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .post(asBody())
            .build();
    }

    public RequestBody asBody() {
        return new FormBody.Builder()
            .add("client_id", clientId)
            .add("client_secret", clientSecret)
            .add("grant_type", grantType)
            .add("code", code)
            .build();
    }
}
