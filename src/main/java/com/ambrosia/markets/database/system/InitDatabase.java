package com.ambrosia.markets.database.system;


import com.ambrosia.markets.database.model.entity.staff.SystemConductor;

public class InitDatabase {

    public static void init() {
        SystemConductor.insertDefaultConductors();
    }
}
