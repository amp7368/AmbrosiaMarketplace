package com.ambrosia.markets.database.model.profile.auction.offer;

import io.ebean.annotation.DbEnumValue;

public enum AuctionOfferStatus {
    CURRENT,
    TAKEN_BACK,
    REJECTED,
    IGNORED,
    ACCEPTED;

    @DbEnumValue
    public String id() {
        return name();
    }
}
