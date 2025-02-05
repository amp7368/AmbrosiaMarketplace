package com.ambrosia.markets.discord.system.theme;

import com.ambrosia.markets.discord.DiscordModule;
import com.ambrosia.markets.discord.base.command.SendMessage;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.slf4j.helpers.CheckReturnValue;

public class AppMessages {

    public static final String NULL_MSG = "N/A";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("LLL dd yyyy")
        .withZone(DiscordModule.TIME_ZONE);

    public static String formatDate(Instant date) {
        return formatDate(date, false);
    }

    public static String formatDate(Instant date, boolean emoji) {
        String dateFormatted = DATE_FORMATTER.format(date);
        if (emoji) return AppEmoji.ANY_DATE + " " + dateFormatted;
        return dateFormatted;
    }

    public static String formatPercentage(double perc) {
        return "%.2f%%".formatted(perc * 100);
    }

    public static AppReply stringMessage(String msg) {
        return new AppReplyString(msg);
    }

    public static class ErrorMessages {

        @CheckReturnValue
        public static AppReply error(String msg) {
            return new AppReplyString(msg);
        }

        @CheckReturnValue
        private static AppReplyMessage error(MessageCreateData msg) {
            return new AppReplyMessage(msg);
        }

        @CheckReturnValue
        public static AppReply missingOption(String option) {
            String msg = String.format("'%s' is required, but was not provided", option);
            return error(msg);
        }

        public static AppReply invalidOption(String optionName, Object optionValue) {
            return error("Invalid %s. '%s' was provided".formatted(optionName, optionValue));
        }

        @CheckReturnValue
        public static AppReply dateParseError(String given, String expected) {
            String msg = "Failed to parse date. %s is not in the format %s".formatted(given, expected);
            return error(msg);
        }
    }

    private record AppReplyString(String msg) implements AppReply, SendMessage {

        @Override
        public void replyError(CommandInteraction event) {
            replyError(event, msg);
        }

        @Override
        public MessageCreateData createMsg() {
            return MessageCreateData.fromContent(msg);
        }

        @Override
        public String toString() {
            return this.msg;
        }
    }

    private record AppReplyMessage(MessageCreateData msg) implements AppReply, SendMessage {

        @Override
        public void replyError(CommandInteraction event) {
            replyError(event, msg);
            msg.close();
        }

        @Override
        public MessageCreateData createMsg() {
            return msg;
        }

        @Override
        public String toString() {
            return this.msg.getContent();
        }
    }
}
