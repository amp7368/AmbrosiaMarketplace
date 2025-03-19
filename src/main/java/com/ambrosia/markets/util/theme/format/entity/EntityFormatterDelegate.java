package com.ambrosia.markets.util.theme.format.entity;

import com.ambrosia.markets.util.theme.format.AppFormattable;
import com.ambrosia.markets.util.theme.format.AppFormatterDelegate;

class EntityFormatterDelegate<T, F extends EntityFormatter<T, ?>> extends AppFormatterDelegate<T, F> implements EntityFormatter<T, F> {

    public EntityFormatterDelegate(AppFormattable<F> createFormatter) {
        super(createFormatter);
    }

    @Override
    public Object getId() {
        return getDelegate().getId();
    }

    @Override
    public String getName() {
        return getDelegate().getName();
    }
}
