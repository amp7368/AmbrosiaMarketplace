package com.ambrosia.markets.api.auth;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import org.jetbrains.annotations.Nullable;

public class TokenRequest {

    public static final Validator<TokenRequest> VALIDATOR = ValidatorBuilder.<TokenRequest>of()
        ._string(TokenRequest::getCode, "code", code -> code.notNull().notBlank().lessThanOrEqual(100))
        .build();

    protected String code;

    @Nullable
    public String getCode() {
        return code;
    }
}
