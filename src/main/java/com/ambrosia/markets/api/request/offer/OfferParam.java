package com.ambrosia.markets.api.request.offer;

import com.ambrosia.markets.database.model.item.api.ItemAuctionApi;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import io.javalin.http.BadRequestResponse;
import java.util.UUID;

public class OfferParam {

    public static DAuctionOffer parse(String offerInput) {
        UUID offerID;
        try {
            offerID = UUID.fromString(offerInput);
        } catch (IllegalArgumentException e) {
            throw new BadRequestResponse("offer must be a UUID. Provided: " + offerInput);
        }
        DAuctionOffer offer = ItemAuctionApi.findOffer(offerID);
        if (offer == null)
            throw new BadRequestResponse("Offer not found! Provided: " + offerInput);
        return offer;
    }

}
