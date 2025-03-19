package com.ambrosia.markets.util.theme.format;

public abstract class AppFormatterInternal<T, F extends AppFormatter<T, ?>> implements AppFormatter<T, F> {

    @Override
    public String toString() {
        return asString();
    }
}
