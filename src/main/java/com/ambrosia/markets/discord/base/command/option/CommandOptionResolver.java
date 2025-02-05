package com.ambrosia.markets.discord.base.command.option;

import java.util.function.Function;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@FunctionalInterface
public interface CommandOptionResolver<Op, R> {

    static <Op, R2, R1> CommandOptionResolver<Op, R2> compose(
        CommandOptionResolver<Op, R1> resolver1,
        CommandOptionResolver<R1, R2> resolver2
    ) {
        return (cmd, mappedOption) -> {
            R1 r1 = resolver1.resolve(cmd, mappedOption);
            return resolver2.resolve(cmd, r1);
        };
    }

    static <R> CommandOptionResolver<R, R> identity() {
        return (cmd, map) -> map;
    }

    R resolve(CommandOptionContext caller, Op mappedOption) throws CommandOptionException;

    default BaseCommandOption<Op, R> build(String name, String description, OptionType optionType,
        Function<OptionMapping, Op> optionMapping) {
        assert name != null;
        assert description != null;
        assert optionType != null;
        assert optionMapping != null;
        return new BaseCommandOption<>(name, description, optionType, optionMapping, this);
    }
}
