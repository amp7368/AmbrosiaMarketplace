package com.ambrosia.markets.api.v1.controller.users.items;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.client.BaseClientRequest;
import com.ambrosia.markets.api.base.item.ListItemsResponse;
import com.ambrosia.markets.api.v1.service.ListItemsService;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class UsersItemsController extends BaseController {

    public void listItems(@NotNull Context ctx) throws Exception {
        String clientInput = ctx.pathParam("user");
        BaseClientRequest request = new BaseClientRequest(ctx, clientInput);
        ListItemsResponse response = ListItemsService.listClientItems(request);
        ctx.json(response);
    }
}
