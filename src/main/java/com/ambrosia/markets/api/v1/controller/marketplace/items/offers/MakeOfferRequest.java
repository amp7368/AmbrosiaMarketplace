package com.ambrosia.markets.api.v1.controller.marketplace.items.offers;

import com.ambrosia.markets.api.request.cost.CostRequest;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import io.javalin.http.BadRequestResponse;

public class MakeOfferRequest {

    private final DClient bidder;
    private final DAuctionItem auction;
    private final CostRequest willingToPay;

    public MakeOfferRequest(DClient bidder, DAuctionItem auction, CostRequest willingToPay) {
        this.bidder = bidder;
        this.auction = auction;
        this.willingToPay = willingToPay;
        validate();
    }

    private void validate() throws BadRequestResponse {
        for (DItemSnapshot item : willingToPay.getItems()) {
            if (DClient.isEqual(this.bidder, item.getOwner())) continue;
            throw new BadRequestResponse("You do not own one of the items in your offer!");
        }
    }

    public DClient getBidder() {
        return bidder;
    }

    public CostRequest getWillingToPay() {
        return willingToPay;
    }

    public DAuctionItem getAuctionItem() {
        return auction;
    }
}
