package com.ambrosia.markets.api.v1.controller.user.me.items.auctions;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;

public class ItemAuctionsUpdateRequestInput {

    // TODO: Finish validator
    public static final Validator<ItemAuctionsUpdateRequestInput> validator = ValidatorBuilder.of(ItemAuctionsUpdateRequestInput.class)
        .build();
    public String listedPrice;
    public Integer durationDays;


    public String listedPrice() {
        return listedPrice;
    }

    public Integer durationDays() {
        return durationDays;
    }
}
