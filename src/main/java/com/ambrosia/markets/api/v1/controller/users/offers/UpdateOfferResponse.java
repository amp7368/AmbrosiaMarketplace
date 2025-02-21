package com.ambrosia.markets.api.v1.controller.users.offers;

import com.ambrosia.markets.api.base.BaseResponse;
import com.ambrosia.markets.api.dto.item.auction.AuctionOfferDto;

public class UpdateOfferResponse extends BaseResponse {

    public final AuctionOfferDto updatedOffer;

    public UpdateOfferResponse(AuctionOfferDto updatedOffer) {
        this.updatedOffer = updatedOffer;
    }
}
