package com.ambrosia.markets.api.base;

import am.ik.yavi.core.ConstraintViolation;
import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.Validator;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController implements Handler {

    protected <T> T validateBody(Context ctx, Validator<T> validator, Class<T> type) {
        T request = ctx.bodyValidator(type).get();
        validate(ctx, validator, request);
        return request;
    }

    protected <T> void validate(Context ctx, Validator<T> validator, T request) throws BadRequestResponse {
        ConstraintViolations validation = validator.validate(request);
        if (validation.isValid()) return;

        Map<String, String> messages = new HashMap<>();
        for (ConstraintViolation error : validation.violations())
            messages.put(error.name(), error.message());
        messages.put("value", ctx.body());
        throw new BadRequestResponse("Bad request", messages);
    }
}
