package com.ambrosia.markets.database.model.entity.staff;

import io.ebean.annotation.DbEnumValue;

public enum Rank {
    CLIENT,
    EMPLOYEE,
    MANAGER,
    ADMINISTRATOR,
    SYSTEM;

    @DbEnumValue
    public String id() {
        return name();
    }
}
