package com.ambrosia.markets.api.system;

import am.ik.yavi.core.ConstraintViolationsException;
import com.ambrosia.markets.api.ApiModule;
import com.ambrosia.markets.util.emerald.EmeraldParserException;
import io.ebean.DuplicateKeyException;
import io.javalin.Javalin;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.ConflictResponse;
import io.javalin.http.Context;

public class ExceptionHandlers {

    public static void registerExceptions(Javalin app) {
        app.exception(DuplicateKeyException.class, ExceptionHandlers::duplicateKeyException);
        app.exception(ConstraintViolationsException.class, ExceptionHandlers::constraintException);
        app.exception(EmeraldParserException.class, ExceptionHandlers::emeraldParseException);
    }

    private static void emeraldParseException(EmeraldParserException e, Context ctx) {
        throw new BadRequestResponse(e.getMessage());
    }

    private static void constraintException(ConstraintViolationsException e, Context context) {
        throw new BadRequestResponse(e.getMessage());
    }

    private static void duplicateKeyException(DuplicateKeyException e, Context ctx) {
        ApiModule.get().logger().error("Error encountered handling {}", ctx.path(), e);
        throw new ConflictResponse("A resource created from this request already exists.");
    }
}
