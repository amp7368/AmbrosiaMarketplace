package com.ambrosia.markets.discord.request;

import com.ambrosia.markets.discord.base.command.BaseCommand;
import com.ambrosia.markets.discord.request.account.RequestAccountCommand;
import discord.util.dcf.slash.DCFSlashSubCommand;
import java.util.List;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class RequestCommand extends BaseCommand {

    @Override
    public List<DCFSlashSubCommand> getSubCommands() {
        return List.of(new RequestAccountCommand());
    }

    @Override
    public SlashCommandData getData() {
        return Commands.slash("request", "Make requests for staff to review");
    }
}
