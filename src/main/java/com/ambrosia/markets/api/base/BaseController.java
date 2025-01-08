package com.ambrosia.markets.api.base;

import am.ik.yavi.core.ConstraintViolation;
import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.Validator;
import com.ambrosia.markets.api.auth.TokenRequest;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController implements Handler {

    protected void validate(Context ctx, Validator<TokenRequest> validator, TokenRequest request) throws BadRequestResponse {
        ConstraintViolations validation = validator.validate(request);
        if (validation.isValid()) return;

        Map<String, String> messages = new HashMap<>();
        for (ConstraintViolation error : validation.violations())
            messages.put(error.name(), error.message());
        messages.put("value", ctx.body());
        throw new BadRequestResponse("Bad request", messages);
    }
}
