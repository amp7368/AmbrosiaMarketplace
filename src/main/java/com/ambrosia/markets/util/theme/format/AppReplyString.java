package com.ambrosia.markets.util.theme.format;

import com.ambrosia.markets.discord.base.command.SendMessage;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

public record AppReplyString(String msg) {

    public void replyError(CommandInteraction event) {
        SendMessage.get().replyError(event, msg);
    }

    public MessageCreateData createBaseMsg() {
        return MessageCreateData.fromContent(msg);
    }

    @Override
    public String toString() {
        return this.msg;
    }
}

