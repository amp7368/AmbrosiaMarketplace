package com.ambrosia.markets.api.v1.controller.users.offers;

import java.util.Collection;
import java.util.stream.Stream;

public enum AuctionUpdateStatus {
    REJECTED;

    public static Collection<String> stringValues() {
        return Stream.of(values())
            .map(Enum::name)
            .toList();
    }
}
