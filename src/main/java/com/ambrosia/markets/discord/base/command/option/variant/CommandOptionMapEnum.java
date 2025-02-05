package com.ambrosia.markets.discord.base.command.option.variant;

import apple.utilities.util.Pretty;
import com.ambrosia.markets.discord.base.command.option.BaseCommandOption;
import com.ambrosia.markets.discord.base.command.option.CommandOptionContext;
import com.ambrosia.markets.discord.base.command.option.CommandOptionException;
import com.ambrosia.markets.discord.base.command.option.CommandOptionException.ParseOptionException;
import com.ambrosia.markets.discord.base.command.option.CommandOptionResolver;
import java.util.List;
import java.util.stream.Stream;
import net.dv8tion.jda.api.interactions.commands.Command.Choice;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class CommandOptionMapEnum<Enm extends Enum<Enm>> implements CommandOptionResolver<String, Enm> {

    private final Class<Enm> enumType;

    public CommandOptionMapEnum(Class<Enm> enumType) {
        this.enumType = enumType;
    }

    private static <E extends Enum<E>> E parseEnum(Class<E> type, String s) {
        try {
            return Enum.valueOf(type, s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static <E extends Enum<E>> BaseCommandOption<String, E> of(String name, String description, Class<E> enumType, E[] values) {
        List<Choice> choices = Stream.of(values)
            .map(E::name)
            .map(Pretty::spaceEnumWords)
            .map(c -> new Choice(c, c))
            .toList();
        return new CommandOptionMapEnum<>(enumType)
            .build(name, description, OptionType.STRING, OptionMapping::getAsString)
            .addChoices(choices);
    }

    @Override
    public Enm resolve(CommandOptionContext ctx, String mappedOption) throws CommandOptionException {
        Enm resolved = parseEnum(this.enumType, mappedOption);
        if (resolved == null) throw new ParseOptionException(ctx.getOptionName(), mappedOption);
        return resolved;
    }
}
