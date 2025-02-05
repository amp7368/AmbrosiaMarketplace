package com.ambrosia.markets.discord.base.command.option.variant;

import com.ambrosia.markets.database.model.entity.client.ClientApi.ClientQueryApi;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.discord.base.command.option.BaseCommandOption;
import com.ambrosia.markets.discord.base.command.option.CommandOptionContext;
import com.ambrosia.markets.discord.base.command.option.CommandOptionException;
import com.ambrosia.markets.discord.base.command.option.CommandOptionException.NoSuchOptionException;
import com.ambrosia.markets.discord.base.command.option.CommandOptionResolver;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class CommandOptionClientResolver implements CommandOptionResolver<String, DClient> {

    public static BaseCommandOption<String, DClient> of() {
        return new CommandOptionClientResolver()
            .build("client", "Client associated with this action", OptionType.STRING, OptionMapping::getAsString);
    }

    @Override
    public DClient resolve(CommandOptionContext caller, String mappedOption) throws CommandOptionException {
        DClient client = ClientQueryApi.findByName(mappedOption);
        if (client == null) {
            throw new NoSuchOptionException(caller, mappedOption);
        }
        return client;
    }
}
