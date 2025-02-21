package com.ambrosia.markets.api.v1.controller.users.offers;

import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;

public record AuctionUpdateStatusRequest(DClient client, DAuctionOffer offer, AuctionUpdateStatus newStatus) {

    public AuctionUpdateStatusRequest(DClient client, DAuctionOffer offer, AuctionUpdateStatusInput input) {
        this(client, offer, input.getStatus());
    }
}
