package com.ambrosia.markets.api.v1.controller.users.offers.status;

import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOfferStatus;
import java.util.Collection;
import java.util.stream.Stream;

public enum AuctionUpdateStatus {
    REJECTED(DAuctionOfferStatus.REJECTED),
    ACCEPTED(DAuctionOfferStatus.ACCEPTED);

    private final DAuctionOfferStatus newDStatus;

    AuctionUpdateStatus(DAuctionOfferStatus newDStatus) {
        this.newDStatus = newDStatus;
    }

    public static Collection<String> stringValues() {
        return Stream.of(values())
            .map(Enum::name)
            .toList();
    }

    public DAuctionOfferStatus getNewDStatus() {
        return newDStatus;
    }
}
