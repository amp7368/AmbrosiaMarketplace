package com.ambrosia.markets.util.theme.format;

import org.jetbrains.annotations.Nullable;

public class AppFormatterDelegate<T, F extends AppFormatter<T, ?>> implements AppFormatter<T, F> {

    private final AppFormattable<F> createFormatter;
    @Nullable
    private F delegate;

    public AppFormatterDelegate(AppFormattable<F> createFormatter) {
        this.createFormatter = createFormatter;
    }

    @Override
    public AppReplyString asReply() {
        return getDelegate().asReply();
    }

    @Override
    public String asString() {
        return getDelegate().asString();
    }

    @Override
    public String toString() {
        return getDelegate().toString();
    }

    protected F getDelegate() {
        if (delegate != null) return delegate;
        return delegate = createFormatter.formatter();
    }
}
