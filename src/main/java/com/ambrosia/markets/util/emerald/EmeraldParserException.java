package com.ambrosia.markets.util.emerald;

import net.dv8tion.jda.api.exceptions.HttpException;

public class EmeraldParserException extends HttpException {

    public EmeraldParserException(String msg) {
        super(msg);
    }
}
