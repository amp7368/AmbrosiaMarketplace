package com.ambrosia.markets.api.v1.controller.marketplace.items.offers;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.client.BaseClientAuthorizationRequest;
import com.ambrosia.markets.api.request.item.ItemParam;
import com.ambrosia.markets.api.v1.service.ItemAuctionService;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.api.ItemApi;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import io.javalin.http.ConflictResponse;
import io.javalin.http.Context;

public class OffersController extends BaseController {

    public void makeOffer(Context ctx) {
        DClient bidder = BaseClientAuthorizationRequest.clientAuthorization(ctx);
        MakeOfferRequestInput input = validateBody(ctx, MakeOfferRequestInput.validator, MakeOfferRequestInput.class);
        DItemSnapshot item = ItemParam.parse(ctx.pathParam("item"));

        DAuctionItem upForSale = item.getCurrentAuction();
        if (upForSale == null) throw new ConflictResponse("The item is currently not up for sale!");

        DAuctionOffer clientOffer = ItemApi.findOffers(upForSale, bidder);
        if (clientOffer != null) throw new ConflictResponse("You already have an offer on this item!");

        MakeOfferRequest request = new MakeOfferRequest(bidder, upForSale,
            input.getEmeralds(), input.getItems(), input.getMiscItems());

        DAuctionOffer offer = ItemAuctionService.createOffer(request);

        ctx.json(new MakeOfferResponse(offer));
    }
}
