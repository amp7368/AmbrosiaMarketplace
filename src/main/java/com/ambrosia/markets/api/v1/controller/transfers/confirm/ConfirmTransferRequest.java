package com.ambrosia.markets.api.v1.controller.transfers.confirm;

import com.ambrosia.markets.api.request.cost.CostRequest;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.UnauthorizedResponse;
import java.util.UUID;

public class ConfirmTransferRequest {

    private final boolean isDiscounted;
    private final CostRequest buyerCost;
    private final CostRequest sellerCost;
    private final DAuctionOffer auction;
    private final boolean sellerRequested;
    private final boolean buyerRequested;

    public ConfirmTransferRequest(ConfirmTransferRequestInput input, DClient requester) throws BadRequestResponse {
        this.auction = input.getAuction();
        this.buyerCost = input.getBuyerCost();
        this.sellerCost = input.getSellerCost();
        this.isDiscounted = input.isDiscounted();

        if (DClient.isEqual(this.auction.getSeller(), requester)) {
            sellerRequested = true;
            buyerRequested = false;
        } else if (DClient.isEqual(this.auction.getBidder(), requester)) {
            buyerRequested = true;
            sellerRequested = false;
        } else
            throw new UnauthorizedResponse("You are not allowed to confirm a request that you didn't create");
        validate();
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public boolean isBuyerRequested() {
        return buyerRequested;
    }

    public boolean isSellerRequested() {
        return sellerRequested;
    }

    public void validate() throws BadRequestResponse {
        UUID auctionedItemId = this.auction.getAuctionItem().getId();

        boolean isSellingAuctionItem = sellerCost
            .getItems()
            .stream()
            .anyMatch(item -> item.getId().equals(auctionedItemId));
        if (!isSellingAuctionItem)
            throw new BadRequestResponse("The trade doesn't include the original auctioned item!");
    }

    public CostRequest getBuyerCost() {
        return buyerCost;
    }

    public CostRequest getSellerCost() {
        return sellerCost;
    }

    public DAuctionOffer getAuction() {
        return auction;
    }
}
