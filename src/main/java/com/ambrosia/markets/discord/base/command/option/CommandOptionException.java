package com.ambrosia.markets.discord.base.command.option;

import com.ambrosia.markets.discord.system.theme.AppMessages.ErrorMessages;
import com.ambrosia.markets.discord.system.theme.AppReply;

public abstract class CommandOptionException extends Exception {

    public abstract AppReply errorMsg();

    @Override
    public String getMessage() {
        return errorMsg().toString();
    }

    public static class ParseOptionException extends CommandOptionException {

        private String optionName;
        private Object optionValue;
        private String message;

        public ParseOptionException(String optionName, Object optionValue) {
            this.optionName = optionName;
            this.optionValue = optionValue;
        }

        public ParseOptionException(String message) {
            this.message = message;
        }

        @Override
        public AppReply errorMsg() {
            if (message != null) return ErrorMessages.error(message);
            return ErrorMessages.invalidOption(optionName, optionValue);
        }
    }

    public static class NoSuchOptionException extends CommandOptionException {

        private final CommandOptionContext context;
        private final Object value;

        public NoSuchOptionException(CommandOptionContext context, Object value) {
            this.context = context;
            this.value = value;
        }

        @Override
        public AppReply errorMsg() {
            String msg = "No %s exists that matches '%s'".formatted(context.getOptionName(), value);
            return ErrorMessages.error(msg);
        }
    }

    public static class MissingOptionException extends CommandOptionException {

        private final String optionName;

        public MissingOptionException(String optionName) {
            this.optionName = optionName;
        }

        @Override
        public AppReply errorMsg() {
            return ErrorMessages.missingOption(optionName);
        }
    }

    public static class ParseDateOptionException extends CommandOptionException {

        private final String dateString;

        public ParseDateOptionException(String dateString) {
            this.dateString = dateString;
        }

        @Override
        public AppReply errorMsg() {
            return ErrorMessages.dateParseError(dateString, "MM/DD/YY");
        }
    }

}
