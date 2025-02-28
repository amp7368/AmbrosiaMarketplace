package com.ambrosia.markets.api.v1.controller.transfers.confirm;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Constraint;
import am.ik.yavi.core.Validator;
import com.ambrosia.markets.api.request.cost.CostInput;
import com.ambrosia.markets.api.request.cost.CostRequest;
import com.ambrosia.markets.api.request.offer.OfferParam;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;

public class ConfirmTransferRequestInput {

    public static Validator<ConfirmTransferRequestInput> validator = validator();
    public String auction;
    public CostInput sellerCost;
    public CostInput buyerCost;
    public boolean isDiscounted = false;

    private static Validator<ConfirmTransferRequestInput> validator() {
        return ValidatorBuilder.of(ConfirmTransferRequestInput.class)
            ._string(c -> c.auction, "auction", c -> c.uuid().notNull())
            ._object(c -> c.sellerCost, "sellerCost", Constraint::notNull)
            .nest(c -> c.sellerCost, "sellerCost", CostInput.allowNullValidator())
            ._object(c -> c.buyerCost, "buyerCost", Constraint::notNull)
            .nest(c -> c.buyerCost, "buyerCost", CostInput.validator())
            .build();
    }

    public DAuctionOffer getAuction() {
        return OfferParam.parse(auction);
    }

    public CostRequest getSellerCost() {
        return new CostRequest(sellerCost);
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public CostRequest getBuyerCost() {
        return new CostRequest(buyerCost);
    }
}
