package com.ambrosia.markets.api.v1.controller.marketplace.items;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.item.ListItemsResponse;
import com.ambrosia.markets.api.v1.service.ListItemsService;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class MarketplaceItemsController extends BaseController {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        ListItemsResponse response = ListItemsService.listAllItems();
        ctx.json(response);
    }
}
