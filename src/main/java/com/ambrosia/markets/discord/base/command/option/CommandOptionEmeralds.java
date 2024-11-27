package com.ambrosia.markets.discord.base.command.option;

import com.ambrosia.markets.util.emerald.Emeralds;
import com.ambrosia.markets.util.emerald.EmeraldsParser;
import com.ambrosia.markets.util.theme.AmbrosiaMessage;
import com.ambrosia.markets.util.theme.AmbrosiaMessages;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class CommandOptionEmeralds extends CommandOptionMulti<String, Emeralds> {

    public CommandOptionEmeralds(String name, String description, OptionType type) {
        super(name, description, type, OptionMapping::getAsString, EmeraldsParser::tryParse);
    }

    @Override
    public AmbrosiaMessage getErrorMessage(CommandInteraction event) {
        String mapped = getMap1(event);
        if (mapped == null) return super.getErrorMessage(event);

        try {
            EmeraldsParser.parse(mapped);
        } catch (NumberFormatException e) {
            return AmbrosiaMessages.stringMessage(e.getMessage());
        }

        return super.getErrorMessage(event);
    }
}
