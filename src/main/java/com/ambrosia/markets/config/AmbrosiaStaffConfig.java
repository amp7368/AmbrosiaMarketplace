package com.ambrosia.markets.config;

import apple.lib.modules.configs.data.config.init.AppleConfigInit;
import com.ambrosia.markets.config.help.HelpCommandConfig;

public class AmbrosiaStaffConfig extends AppleConfigInit {

    private static AmbrosiaStaffConfig instance;

    protected HelpCommandConfig helpCommand = new HelpCommandConfig();

    public AmbrosiaStaffConfig() {
        instance = this;
    }

    public static AmbrosiaStaffConfig get() {
        return instance;
    }

    public HelpCommandConfig getHelp() {
        return helpCommand;
    }
}
