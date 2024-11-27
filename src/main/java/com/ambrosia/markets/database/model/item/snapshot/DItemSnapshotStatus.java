package com.ambrosia.markets.database.model.item.snapshot;

import io.ebean.annotation.DbEnumValue;

public enum DItemSnapshotStatus {
    OWNED,
    TRANSFERRED,
    LOST,
    FOUND;

    @DbEnumValue
    public String id() {
        return name();
    }

}
