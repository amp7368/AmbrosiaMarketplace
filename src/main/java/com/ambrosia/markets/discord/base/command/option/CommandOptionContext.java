package com.ambrosia.markets.discord.base.command.option;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public interface CommandOptionContext {

    String getOptionName();

    OptionType getOptionType();

    String getOptionDescription();
}
