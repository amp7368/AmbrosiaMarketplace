package com.ambrosia.markets.api.v1.controller.marketplace.items.offers;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import com.ambrosia.markets.api.request.cost.CostInput;

public class MakeOfferRequestInput extends CostInput {

    public static Validator<MakeOfferRequestInput> validator = offerValidator();

    public static Validator<MakeOfferRequestInput> offerValidator() {
        return ValidatorBuilder.of(MakeOfferRequestInput.class)
            .nest(c -> c, "", CostInput.validator())
            .build();
    }
}
