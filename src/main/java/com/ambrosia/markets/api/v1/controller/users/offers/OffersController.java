package com.ambrosia.markets.api.v1.controller.users.offers;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.client.BaseClientAuthorizationRequest;
import com.ambrosia.markets.api.dto.item.ListOffersResponse;
import com.ambrosia.markets.api.dto.item.auction.AuctionOfferDto;
import com.ambrosia.markets.api.request.offer.OfferParam;
import com.ambrosia.markets.api.v1.controller.users.offers.status.AuctionUpdateStatusInput;
import com.ambrosia.markets.api.v1.controller.users.offers.status.AuctionUpdateStatusRequest;
import com.ambrosia.markets.api.v1.service.ItemAuctionService;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import io.javalin.http.ConflictResponse;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import java.util.List;

public class OffersController extends BaseController {

    public void listClientOffers(Context ctx) {
        DClient client = BaseClientAuthorizationRequest.clientAuthorization(ctx);
        List<AuctionOfferDto> offers = ItemAuctionService.listOffers(client);
        ctx.json(new ListOffersResponse(offers));
    }

    public void updateStatus(Context ctx) {
        DClient client = BaseClientAuthorizationRequest.clientAuthorization(ctx);
        AuctionUpdateStatusInput input = validateBody(ctx, AuctionUpdateStatusInput.validator, AuctionUpdateStatusInput.class);
        DAuctionOffer offer = OfferParam.parse(ctx.pathParam("offer"));
        if (DClient.isEqual(client, offer.getSeller()))
            throw new UnauthorizedResponse("You cannot reject an offer on someone else's item!");
        if (offer.isCompleted())
            throw new ConflictResponse("Cannot update an offer that's already finalized!");

        AuctionUpdateStatusRequest request = new AuctionUpdateStatusRequest(client, offer, input);
        AuctionOfferDto updated = ItemAuctionService.updateStatus(request);
        ctx.json(new UpdateOfferResponse(updated));
    }
}
