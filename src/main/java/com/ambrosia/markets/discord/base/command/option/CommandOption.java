package com.ambrosia.markets.discord.base.command.option;

import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.discord.base.command.option.variant.CommandOptionClientResolver;
import com.ambrosia.markets.discord.base.command.option.variant.CommandOptionDateResolver;
import com.ambrosia.markets.discord.system.theme.AppReply;
import java.time.Instant;
import java.util.List;
import java.util.function.Function;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message.Attachment;
import net.dv8tion.jda.api.interactions.commands.Command.Choice;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.Nullable;

public interface CommandOption<Op, R> {

    CommandOption<String, Instant> DATE = CommandOptionDateResolver.of();
    CommandOption<String, DClient> CLIENT = CommandOptionClientResolver.of();
    CommandOption<Member, Member> DISCORD = base("discord", "The discord of the client", OptionType.MENTIONABLE,
        OptionMapping::getAsMember);
    CommandOption<String, String> MINECRAFT = base("minecraft", "Your minecraft username", OptionType.STRING,
        OptionMapping::getAsString);
    CommandOption<String, String> DISPLAY_NAME = base("display_name", "The name to display on the profile", OptionType.STRING,
        OptionMapping::getAsString);
    CommandOption<Attachment, Attachment> IMAGE = base("image", "The image to add", OptionType.ATTACHMENT,
        OptionMapping::getAsAttachment);

    static <T> CommandOption<T, T> base(String name, String description, OptionType optionType,
        Function<OptionMapping, T> optionMapping) {
        return new BaseCommandOption<>(name, description, optionType, optionMapping, (ctx, op) -> op);
    }

    <R2> BaseCommandOption<Op, R2> withResolver(CommandOptionResolver<R, R2> resolver2);

    R getOptional(CommandInteraction event, R fallback);

    @Nullable
    default R getOptional(CommandInteraction event) {
        return this.getOptional(event, null);
    }

    R getRequired(CommandInteraction event, @Nullable AppReply errorMsg);

    default R getRequired(CommandInteraction event) {
        return getRequired(event, null);
    }

    default void addOption(SubcommandData command) {
        this.addOption(command, false);
    }

    default void addOption(SlashCommandData command) {
        this.addOption(command, false);
    }

    void addOption(SubcommandData command, boolean required);

    void addOption(SlashCommandData command, boolean required);

    OptionData createOption(boolean required);

    CommandOption<Op, R> addChoices(List<Choice> choices);

    CommandOption<Op, R> setAutocomplete();
}
