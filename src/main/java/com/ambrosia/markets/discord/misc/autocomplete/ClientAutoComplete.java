package com.ambrosia.markets.discord.misc.autocomplete;

import com.ambrosia.markets.config.discord.DiscordPermissions;
import com.ambrosia.markets.database.model.entity.client.ClientSearch;
import java.util.List;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command.Choice;
import org.jetbrains.annotations.NotNull;

public class ClientAutoComplete extends AmbrosiaAutoComplete<String> {

    public ClientAutoComplete(String optionName) {
        super(optionName);
    }

    @Override
    protected List<String> autoComplete(@NotNull CommandAutoCompleteInteractionEvent event, String arg) {
        if (!event.isFromGuild()) return List.of();

        boolean isEmployee = DiscordPermissions.get().isEmployee(event.getMember());
        if (!isEmployee) return List.of();

        return ClientSearch.autoComplete(arg);
    }

    @NotNull
    @Override
    protected Choice choice(String name) {
        return new Choice(name, name);
    }
}
