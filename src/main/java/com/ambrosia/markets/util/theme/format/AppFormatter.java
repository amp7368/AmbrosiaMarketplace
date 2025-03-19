package com.ambrosia.markets.util.theme.format;

public interface AppFormatter<T, F extends AppFormatter<T, ?>> {

    default AppReplyString asReply() {
        return new AppReplyString(asString());
    }

    String asString();
}
