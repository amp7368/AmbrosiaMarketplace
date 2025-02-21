package com.ambrosia.markets.api.dto.item;

import com.ambrosia.markets.api.base.BaseResponse;
import com.ambrosia.markets.api.dto.item.auction.AuctionOfferDto;
import java.util.List;

public class ListOffersResponse extends BaseResponse {

    public final List<AuctionOfferDto> offers;

    public ListOffersResponse(List<AuctionOfferDto> offers) {
        this.offers = offers;
    }
}
