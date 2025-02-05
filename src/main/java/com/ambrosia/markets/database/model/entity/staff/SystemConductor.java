package com.ambrosia.markets.database.model.entity.staff;

import com.ambrosia.markets.database.model.entity.actor.UserActor;
import java.util.UUID;

public enum SystemConductor {
    SYSTEM("System", UUID.fromString("c6528e00-0000-0000-0000-000000000000")),
    WEB("Web", UUID.fromString("c6528e00-0000-0000-0000-000000000001"));


    private final UUID id;
    private final String name;
    private DStaffConductor conductor;

    SystemConductor(String name, UUID id) {
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

    public UUID getId() {
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
