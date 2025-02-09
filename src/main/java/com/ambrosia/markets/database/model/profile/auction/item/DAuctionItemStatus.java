package com.ambrosia.markets.database.model.profile.auction.item;


import io.ebean.annotation.DbEnumValue;

public enum DAuctionItemStatus {
    CURRENT,
    TERMINATED;

    @DbEnumValue
    public String dbValue() {
        return this.name();
    }

}
