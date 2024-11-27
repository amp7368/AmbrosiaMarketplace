package com.ambrosia.markets.database.model.entity.staff;

import com.ambrosia.markets.database.model.entity.actor.UserActor;

public enum SystemConductor {
    SYSTEM("System", 1),
    WEB("Web", 2);


    private final int id;
    private final String name;
    private DStaffConductor conductor;

    SystemConductor(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static void insertDefaultConductors() {
        // id of 0 will make it auto-assign an id
        for (SystemConductor system : SystemConductor.values()) {
            system.insert();
        }
    }

    private void insert() {
        this.conductor = DStaffConductor.insertSystemConductor(name, id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DStaffConductor db() {
        return conductor;
    }

    public UserActor actor() {
        return UserActor.of(conductor);
    }
}
