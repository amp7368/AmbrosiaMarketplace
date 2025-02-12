package com.ambrosia.markets.api.v1.controller.users;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.client.BaseClientRequest;
import com.ambrosia.markets.api.dto.user.PublicUserResponse;
import com.ambrosia.markets.api.v1.service.ProfileService;
import io.javalin.http.Context;

public class UsersController extends BaseController {

    public void getProfile(Context ctx) {
        String clientInput = ctx.pathParam("user");
        BaseClientRequest request = new BaseClientRequest(ctx, clientInput);

        PublicUserResponse response = ProfileService.viewPublicProfile(request);
        ctx.json(response);
    }
}
