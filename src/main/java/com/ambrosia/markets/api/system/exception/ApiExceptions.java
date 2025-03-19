package com.ambrosia.markets.api.system.exception;

import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.system.exception.AlreadySoldException;
import io.javalin.http.ExpectationFailedResponse;
import org.jetbrains.annotations.CheckReturnValue;
import org.jetbrains.annotations.NotNull;

public class ApiExceptions {

    @NotNull
    @CheckReturnValue
    public static ExpectationFailedResponse alreadySold(DClient newOwner, AlreadySoldException cause) {
        String itemSoldMsg = AlreadySoldException.alreadySoldMsg(cause.getItem());
        String msg = "Cannot sell to %s. %s"
            .formatted(newOwner.getEffectiveName(), itemSoldMsg);
        return new ExpectationFailedResponse(msg);
    }

    @NotNull
    @CheckReturnValue
    public static ExpectationFailedResponse cannotOffer(AlreadySoldException e) {
        String msg = "%s has already been transferred".formatted(e.getItem().formatter());
        return new ExpectationFailedResponse(msg);
    }
}
