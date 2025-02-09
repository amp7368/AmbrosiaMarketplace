package com.ambrosia.markets.api.v1.controller.user.me.items.auctions;

import com.ambrosia.markets.api.base.client.BaseClientAuthorizationRequest;
import com.ambrosia.markets.database.model.item.ItemApi;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.util.emerald.EmeraldParserException;
import com.ambrosia.markets.util.emerald.Emeralds;
import com.ambrosia.markets.util.emerald.EmeraldsParser;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import java.util.Optional;
import java.util.UUID;

public class ItemAuctionsUpdateRequest extends BaseClientAuthorizationRequest {

    private final DItemSnapshot item;
    private final Emeralds listedPrice;
    private final int durationDays;

    public ItemAuctionsUpdateRequest(Context ctx, ItemAuctionsUpdateRequestInput input) {
        super(ctx);
        try {
            String itemIdInput = ctx.pathParam("item");
            UUID itemId = UUID.fromString(itemIdInput);
            item = ItemApi.findItem(itemId);
            if (item == null)
                throw new BadRequestResponse("Item id of '" + itemId + "' not found");
        } catch (IllegalArgumentException e) {
            throw new BadRequestResponse("Item query param. Please provide a valid UUID");
        }
        try {
            listedPrice = EmeraldsParser.parse(input.listedPrice());
        } catch (EmeraldParserException e) {
            throw new BadRequestResponse(e.getMessage());
        }
        this.durationDays = Optional.ofNullable(input.durationDays())
            .orElse(7);
    }

    public DItemSnapshot getItem() {
        return item;
    }

    public Emeralds getListedPrice() {
        return listedPrice;
    }

    public int getDurationDays() {
        return durationDays;
    }
}
