package com.ambrosia.markets.api.base.item;

import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItemStatus;
import java.time.Instant;

public class ItemSnapshotAuctionDto {

    public final Instant startSaleAt;
    public final DAuctionItemStatus status;
    public final Instant endSaleAt;
    public final long listingPrice;

    public ItemSnapshotAuctionDto(DAuctionItem auction) {
        this.listingPrice = auction.getListingPrice();
        this.startSaleAt = auction.getStartSaleAt();
        this.endSaleAt = auction.getEndSaleAt();
        this.status = auction.getStatus();
    }
}
