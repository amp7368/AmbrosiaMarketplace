package com.ambrosia.markets.api.v1.controller.users.items;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.item.ListItemsResponse;
import com.ambrosia.markets.api.v1.service.ListItemsService;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class UsersItemsController extends BaseController {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String clientInput = ctx.pathParam("user");
        UsersItemsRequest request = new UsersItemsRequest(ctx, clientInput);
        ListItemsResponse response = ListItemsService.listClientItems(request);
        ctx.json(response);
    }
}
