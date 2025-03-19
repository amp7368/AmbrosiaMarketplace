package com.ambrosia.markets.util.theme.format.entity;

import com.ambrosia.markets.util.theme.format.AppFormatterInternal;
import java.util.function.Function;

class EntityFormatterInternal<T, F extends EntityFormatter<T, ?>> extends AppFormatterInternal<T, F> implements EntityFormatter<T, F> {

    private final T item;
    private final Function<T, Object> getId;
    private final Function<T, String> getName;

    EntityFormatterInternal(T item, Function<T, Object> getId, Function<T, String> getName) {
        this.item = item;
        this.getId = getId;
        this.getName = getName;
    }

    @Override
    public String asString() {
        return "%s (id %s)".formatted(getName(), getId());
    }

    @Override
    public Object getId() {
        return getId.apply(item);
    }

    @Override
    public String getName() {
        return getName.apply(item);
    }
}
