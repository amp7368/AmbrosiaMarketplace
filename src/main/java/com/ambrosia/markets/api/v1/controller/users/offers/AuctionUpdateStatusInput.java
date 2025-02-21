package com.ambrosia.markets.api.v1.controller.users.offers;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import io.javalin.http.BadRequestResponse;

public class AuctionUpdateStatusInput {

    public static Validator<AuctionUpdateStatusInput> validator = validator();
    private String status;

    private static Validator<AuctionUpdateStatusInput> validator() {
        return ValidatorBuilder.of(AuctionUpdateStatusInput.class)
            ._string(input -> input.status, "status", c -> c.oneOf(AuctionUpdateStatus.stringValues()))
            .build();
    }

    public AuctionUpdateStatus getStatus() {
        try {
            return AuctionUpdateStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new BadRequestResponse("status is not a valid value");
        }
    }
}
