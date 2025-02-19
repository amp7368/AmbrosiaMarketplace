package com.ambrosia.markets.api.v1.controller.marketplace.items.offers;

import com.ambrosia.markets.api.request.miscitem.MiscItemRequest;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.util.emerald.Emeralds;
import io.javalin.http.BadRequestResponse;
import java.util.List;
import java.util.UUID;

public class MakeOfferRequest {

    private final DClient bidder;
    private final Emeralds willingToPay;
    private final List<DItemSnapshot> items;
    private final List<MiscItemRequest> miscItems;

    private final DAuctionItem auction;

    public MakeOfferRequest(DClient bidder,
        DAuctionItem auction,
        Emeralds willingToPay,
        List<DItemSnapshot> items,
        List<MiscItemRequest> miscItems) {
        this.bidder = bidder;
        this.auction = auction;
        this.willingToPay = willingToPay;
        this.items = items;
        this.miscItems = miscItems;
        validate();
    }

    private void validate() throws BadRequestResponse {
        UUID bidderId = this.bidder.getId();
        for (DItemSnapshot item : items) {
            if (bidderId.equals(item.getOwner().getId())) continue;
            throw new BadRequestResponse("You do not own one of the items in your offer!");
        }
    }

    public DClient getBidder() {
        return bidder;
    }

    public Emeralds getWillingToPay() {
        return willingToPay;
    }

    public List<DItemSnapshot> getItems() {
        return items;
    }

    public List<MiscItemRequest> getMiscItems() {
        return miscItems;
    }

    public DAuctionItem getAuctionItem() {
        return auction;
    }

    public boolean isExtended() {
        return !items.isEmpty() || !miscItems.isEmpty();
    }
}
