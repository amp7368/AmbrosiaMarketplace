package com.ambrosia.markets.api.v1.controller.user.me;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.client.BaseClientAuthorizationRequest;
import com.ambrosia.markets.api.dto.user.PublicUserResponse;
import com.ambrosia.markets.api.v1.service.ProfileService;
import io.javalin.http.Context;

public class ProfileController extends BaseController {

    public void getProfile(Context ctx) {
        BaseClientAuthorizationRequest request = new BaseClientAuthorizationRequest(ctx);
        PublicUserResponse response = ProfileService.viewPrivateProfile(request);
        ctx.json(response);
    }
}
