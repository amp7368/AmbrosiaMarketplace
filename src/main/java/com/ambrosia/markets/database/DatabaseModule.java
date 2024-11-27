package com.ambrosia.markets.database;

import apple.lib.ebean.database.AppleEbeanDatabaseMetaConfig;
import apple.lib.modules.AppleModule;
import com.ambrosia.markets.Ambrosia;
import com.ambrosia.markets.database.system.InitDatabase;

public class DatabaseModule extends AppleModule {


    private static DatabaseModule instance;

    public DatabaseModule() {
        instance = this;
    }

    public static DatabaseModule get() {
        return instance;
    }

    @Override
    public void onLoad() {
        AppleEbeanDatabaseMetaConfig.configureMeta(
            Ambrosia.class,
            Ambrosia.get().getDataFolder(),
            logger()::error,
            logger()::info);

        new AmbrosiaDatabase();
        InitDatabase.init();
    }

    @Override
    public String getName() {
        return "Database";
    }
}
