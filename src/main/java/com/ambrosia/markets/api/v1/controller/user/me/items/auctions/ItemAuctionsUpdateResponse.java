package com.ambrosia.markets.api.v1.controller.user.me.items.auctions;

import com.ambrosia.markets.api.base.BaseResponse;
import com.ambrosia.markets.api.dto.item.ItemSnapshotDto;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;

public class ItemAuctionsUpdateResponse extends BaseResponse {

    public final ItemSnapshotDto auctionedItem;

    public ItemAuctionsUpdateResponse(DItemSnapshot auctionedItem) {
        this.auctionedItem = new ItemSnapshotDto(auctionedItem);
    }
}
