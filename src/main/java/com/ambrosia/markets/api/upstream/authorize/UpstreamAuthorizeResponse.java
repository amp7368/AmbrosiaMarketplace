package com.ambrosia.markets.api.upstream.authorize;

public class UpstreamAuthorizeResponse {

    protected String access_token;

    private UpstreamAuthorizeResponse(String access_token) {
        this.access_token = access_token;
    }

    public static UpstreamAuthorizeResponse create(String accessToken) {
        // todo create a random sessionToken for client access
        return new UpstreamAuthorizeResponse(accessToken);
    }
}
