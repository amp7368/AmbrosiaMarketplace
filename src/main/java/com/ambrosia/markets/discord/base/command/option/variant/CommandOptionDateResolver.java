package com.ambrosia.markets.discord.base.command.option.variant;

import com.ambrosia.markets.discord.DiscordModule;
import com.ambrosia.markets.discord.base.command.option.BaseCommandOption;
import com.ambrosia.markets.discord.base.command.option.CommandOptionContext;
import com.ambrosia.markets.discord.base.command.option.CommandOptionException;
import com.ambrosia.markets.discord.base.command.option.CommandOptionException.ParseDateOptionException;
import com.ambrosia.markets.discord.base.command.option.CommandOptionResolver;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class CommandOptionDateResolver implements CommandOptionResolver<String, Instant> {

    public static final DateTimeFormatter SIMPLE_DATE_FORMATTER = new DateTimeFormatterBuilder()
        .appendPattern("MM/dd/yy")
        .parseDefaulting(ChronoField.SECOND_OF_DAY, 0)
        .parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
        .toFormatter()
        .withZone(DiscordModule.TIME_ZONE);

    public CommandOptionDateResolver() {
    }

    public static BaseCommandOption<String, Instant> of() {
        return new CommandOptionDateResolver()
            .build("date", "Date (MM/DD/YY)", OptionType.STRING, OptionMapping::getAsString);
    }

    @Override
    public Instant resolve(CommandOptionContext ctx, String mappedOption) throws CommandOptionException {
        try {
            TemporalAccessor parsed = SIMPLE_DATE_FORMATTER.parse(mappedOption);
            Instant date = Instant.from(parsed);
            if (ChronoUnit.DAYS.between(date, Instant.now()) == 0)
                return Instant.now();
            return date;
        } catch (DateTimeParseException e) {
            throw new ParseDateOptionException(mappedOption);
        }
    }
}
