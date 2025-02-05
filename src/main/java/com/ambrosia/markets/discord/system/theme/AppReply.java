package com.ambrosia.markets.discord.system.theme;

import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

public interface AppReply {

    void replyError(CommandInteraction event);

    MessageCreateData createMsg();
}
