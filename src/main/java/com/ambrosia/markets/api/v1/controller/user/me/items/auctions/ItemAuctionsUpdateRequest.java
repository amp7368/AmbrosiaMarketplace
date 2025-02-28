package com.ambrosia.markets.api.v1.controller.user.me.items.auctions;

import com.ambrosia.markets.api.base.client.BaseClientAuthorizationRequest;
import com.ambrosia.markets.api.base.client.IClientRequest;
import com.ambrosia.markets.api.request.item.ItemParam;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.util.emerald.Emeralds;
import com.ambrosia.markets.util.emerald.EmeraldsParser;
import io.javalin.http.Context;
import java.util.Optional;

public class ItemAuctionsUpdateRequest implements IClientRequest {

    private final DClient client;
    private final DItemSnapshot item;
    private final Emeralds listedPrice;
    private final int durationDays;

    public ItemAuctionsUpdateRequest(DClient client, DItemSnapshot item, Emeralds listedPrice, int durationDays) {
        this.client = client;
        this.item = item;
        this.listedPrice = listedPrice;
        this.durationDays = durationDays;
    }

    public static ItemAuctionsUpdateRequest parse(Context ctx, ItemAuctionsUpdateRequestInput input) {
        DClient client = BaseClientAuthorizationRequest.clientAuthorization(ctx);

        String itemInput = ctx.pathParam("item");
        DItemSnapshot item = ItemParam.parse(itemInput);

        Emeralds listedPrice = EmeraldsParser.parseNoZero(input.listedPrice());
        int durationDays = Optional.ofNullable(input.durationDays())
            .orElse(7);
        return new ItemAuctionsUpdateRequest(client, item, listedPrice, durationDays);
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

    @Override
    public DClient getClient() {
        return client;
    }
}
