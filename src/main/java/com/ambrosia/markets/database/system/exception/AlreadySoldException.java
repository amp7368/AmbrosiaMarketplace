package com.ambrosia.markets.database.system.exception;

import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import org.jetbrains.annotations.NotNull;

public class AlreadySoldException extends Exception {

    private final DItemSnapshot item;

    public AlreadySoldException(@NotNull DItemSnapshot item) {
        this.item = item;
    }

    @NotNull
    public static String alreadySoldMsg(@NotNull DItemSnapshot item) {
        return "The %s with id %s was already sold.".formatted(item.getData().name(), item.getId());
    }

    @Override
    public String getMessage() {
        return alreadySoldMsg(item);
    }

    public DItemSnapshot getItem() {
        return item;
    }
}
