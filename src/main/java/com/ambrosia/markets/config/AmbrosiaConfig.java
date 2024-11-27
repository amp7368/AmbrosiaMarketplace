package com.ambrosia.markets.config;

import com.ambrosia.markets.config.discord.DiscordConfig;
import com.ambrosia.markets.config.discord.DiscordPermissions;
import com.ambrosia.markets.database.AmbrosiaDatabase.AmbrosiaDatabaseConfig;

public class AmbrosiaConfig {

    private static AmbrosiaConfig instance;
    public boolean isProduction = true;
    public DiscordConfig discord = new DiscordConfig();
    public DiscordPermissions discordPermissions = new DiscordPermissions();
    public AmbrosiaDatabaseConfig database = new AmbrosiaDatabaseConfig();

    public AmbrosiaConfig() {
        instance = this;
    }

    public static AmbrosiaConfig get() {
        return instance;
    }

    public static AmbrosiaStaffConfig staff() {
        return AmbrosiaStaffConfig.get();
    }

    public boolean isProduction() {
        return this.isProduction;
    }
}