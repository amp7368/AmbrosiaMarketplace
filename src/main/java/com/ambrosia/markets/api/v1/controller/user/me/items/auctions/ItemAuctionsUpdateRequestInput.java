package com.ambrosia.markets.api.v1.controller.user.me.items.auctions;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;

public record ItemAuctionsUpdateRequestInput(
    String listedPrice,
    Integer durationDays
) {

    // TODO: Finish validator
    public static final Validator<ItemAuctionsUpdateRequestInput> VALIDATOR = ValidatorBuilder.of(ItemAuctionsUpdateRequestInput.class)
        .build();
}
