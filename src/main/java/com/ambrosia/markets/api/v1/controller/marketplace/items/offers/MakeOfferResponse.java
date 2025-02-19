package com.ambrosia.markets.api.v1.controller.marketplace.items.offers;

import com.ambrosia.markets.api.base.BaseResponse;
import com.ambrosia.markets.api.dto.item.auction.AuctionOfferDto;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;

public class MakeOfferResponse extends BaseResponse {

    public final AuctionOfferDto createdOffer;

    public MakeOfferResponse(DAuctionOffer createdOffer) {
        this.createdOffer = new AuctionOfferDto(createdOffer);
    }
}
