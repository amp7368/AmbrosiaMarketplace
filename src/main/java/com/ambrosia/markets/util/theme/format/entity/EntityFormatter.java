package com.ambrosia.markets.util.theme.format.entity;

import com.ambrosia.markets.util.theme.format.AppFormattable;
import com.ambrosia.markets.util.theme.format.AppFormatter;
import io.ebean.DB;
import io.ebean.Model;
import java.util.function.Function;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface EntityFormatter<T, F extends EntityFormatter<T, ?>> extends AppFormatter<T, F> {

    @NotNull
    @Contract("_ -> new")
    static <T, F extends EntityFormatter<T, ?>> EntityFormatter<T, F> prop(
        AppFormattable<F> createFormatter) {
        return new EntityFormatterDelegate<>(createFormatter);
    }

    @NotNull
    @Contract("_, _ -> new")
    static <T extends Model, F extends EntityFormatter<T, F>> EntityFormatter<T, F> create(
        T item, Function<T, String> getName) {
        return create(item, DB::beanId, getName);
    }

    @Contract("_, _, _ -> new")
    static <T, F extends EntityFormatter<T, F>> @NotNull EntityFormatter<T, F> create(T item,
        Function<T, Object> getId,
        Function<T, String> getName) {
        return new EntityFormatterInternal<>(item, getId, getName);
    }

    Object getId();

    String getName();

}
