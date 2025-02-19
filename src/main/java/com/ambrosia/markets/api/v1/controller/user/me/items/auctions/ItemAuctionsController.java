package com.ambrosia.markets.api.v1.controller.user.me.items.auctions;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.v1.service.ItemAuctionService;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class ItemAuctionsController extends BaseController {

    public void markForAuction(@NotNull Context ctx) throws Exception {
        ItemAuctionsUpdateRequestInput input = validateBody(ctx, ItemAuctionsUpdateRequestInput.validator,
            ItemAuctionsUpdateRequestInput.class);

        ItemAuctionsUpdateRequest request = ItemAuctionsUpdateRequest.parse(ctx, input);
        DAuctionItem item = ItemAuctionService.markForAuction(request);
        ctx.json(new ItemAuctionsUpdateResponse(item.getItem()));
    }
}
