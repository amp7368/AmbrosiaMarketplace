package com.ambrosia.markets.database.model.interaction;

import io.ebean.annotation.DbEnumValue;

public enum ClientClickTarget {
    ITEM,
    CLIENT,
    AUCTION;

    @DbEnumValue
    public String id() {
        return this.name();
    }
}
