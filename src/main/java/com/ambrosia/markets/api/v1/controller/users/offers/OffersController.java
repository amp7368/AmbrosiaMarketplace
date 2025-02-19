package com.ambrosia.markets.api.v1.controller.users.offers;

import com.ambrosia.markets.api.base.client.BaseClientAuthorizationRequest;
import com.ambrosia.markets.api.dto.item.auction.AuctionOfferDto;
import com.ambrosia.markets.api.v1.controller.marketplace.items.offers.ListOffersResponse;
import com.ambrosia.markets.api.v1.service.ItemAuctionService;
import com.ambrosia.markets.database.model.entity.client.DClient;
import io.javalin.http.Context;
import java.util.List;

public class OffersController {

    public void listClientOffers(Context ctx) {
        DClient client = BaseClientAuthorizationRequest.clientAuthorization(ctx);
        List<AuctionOfferDto> offers = ItemAuctionService.listOffers(client);
        ctx.json(new ListOffersResponse(offers));
    }
}
