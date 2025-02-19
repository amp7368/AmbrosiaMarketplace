package com.ambrosia.markets.api.v1.controller.marketplace.items;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.dto.item.ListItemsResponse;
import com.ambrosia.markets.api.v1.service.ListItemsService;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class MarketplaceItemsController extends BaseController {

    public void listItems(@NotNull Context ctx) throws Exception {
        // TODO query string determines filter
        ListItemsResponse response = ListItemsService.listMarketplaceItems();
        ctx.json(response);
    }
}
