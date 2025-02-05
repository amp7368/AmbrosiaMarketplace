package com.ambrosia.markets.discord.base.command.option;

import com.ambrosia.markets.discord.base.command.option.CommandOptionException.MissingOptionException;
import com.ambrosia.markets.discord.base.command.option.CommandOptionException.ParseOptionException;
import com.ambrosia.markets.discord.system.theme.AppReply;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import net.dv8tion.jda.api.interactions.commands.Command.Choice;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

public class BaseCommandOption<Op, R> implements CommandOption<Op, R>, CommandOptionContext {

    protected final String description;
    protected final String name;
    protected final OptionType type;
    private final Function<OptionMapping, Op> optionMapping;
    private final CommandOptionResolver<Op, R> resolver;
    private boolean autoComplete = false;
    private List<Choice> choices;

    protected BaseCommandOption(String name, String description, OptionType type,
        Function<OptionMapping, Op> optionMapping, CommandOptionResolver<Op, R> resolver) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.optionMapping = optionMapping;
        this.resolver = resolver;
    }

    @Override
    public final <R2> BaseCommandOption<Op, R2> withResolver(CommandOptionResolver<R, R2> resolver2) {
        CommandOptionResolver<Op, R2> newResolver = CommandOptionResolver.compose(this.resolver, resolver2);
        return new BaseCommandOption<>(name, description, type, optionMapping, newResolver);
    }

    @Override
    @UnknownNullability
    public R getOptional(CommandInteraction event, R fallback) {
        Op commandOption = event.getOption(name, this.optionMapping);
        if (commandOption == null) return fallback;
        try {
            return resolver.resolve(this, commandOption);
        } catch (CommandOptionException e) {
            return null;
        }
    }

    @Override
    public R getRequired(CommandInteraction event, @Nullable AppReply overrideErrorMsg) {
        try {
            return resolve(event);
        } catch (CommandOptionException e) {
            AppReply errorMsg = Objects.requireNonNullElseGet(overrideErrorMsg, e::errorMsg);
            errorMsg.replyError(event);
            return null;
        }
    }

    @Override
    public void addOption(SubcommandData command, boolean required) {
        command.addOptions(createOption(required));
    }

    @Override
    public void addOption(SlashCommandData command, boolean required) {
        command.addOptions(createOption(required));
    }

    @Override
    public OptionData createOption(boolean required) {
        OptionData optionData = new OptionData(getOptionType(), getOptionName(), getOptionDescription(), required, autoComplete);
        if (choices != null) optionData.addChoices(this.choices);
        return optionData;
    }

    @Override
    public BaseCommandOption<Op, R> addChoices(List<Choice> choices) {
        if (this.choices == null) this.choices = new ArrayList<>();
        this.choices.addAll(choices);
        return this;
    }

    @Override
    public BaseCommandOption<Op, R> setAutocomplete() {
        this.autoComplete = true;
        return this;
    }

    @Override
    public String getOptionName() {
        return this.name;
    }

    @Override
    public OptionType getOptionType() {
        return type;
    }

    @Override
    public String getOptionDescription() {
        return description;
    }

    public R resolve(CommandInteraction event) throws CommandOptionException {
        Op option = event.getOption(name, this.optionMapping);
        if (option == null) throw new MissingOptionException(getOptionName());

        R result = resolver.resolve(this, option);
        if (result == null) throw new ParseOptionException(getOptionName(), option);

        return result;
    }
}
