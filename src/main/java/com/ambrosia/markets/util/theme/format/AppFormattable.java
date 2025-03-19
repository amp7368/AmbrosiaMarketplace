package com.ambrosia.markets.util.theme.format;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface AppFormattable<Formatter> {

    @NotNull
    Formatter formatter();
}
