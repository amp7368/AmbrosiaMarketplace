package com.ambrosia.markets.database.model.trade.transfer;

import io.ebean.annotation.DbEnumValue;

public enum DTransferType {
    TRADE,
    DISCOUNTED,
    GIFT,
    LOAN,
    LOST;

    @DbEnumValue
    public String id() {
        return name();
    }

}
