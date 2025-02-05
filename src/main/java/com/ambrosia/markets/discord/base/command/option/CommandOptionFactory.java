package com.ambrosia.markets.discord.base.command.option;

import java.util.function.Function;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public interface CommandOptionFactory {


    static <R> BaseCommandOption<R, R> create(String name, String description, OptionType optionType,
        Function<OptionMapping, R> optionMapping) {
        return new BaseCommandOption<>(name, description, optionType, optionMapping, CommandOptionResolver.identity());
    }
}
