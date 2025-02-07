package com.ambrosia.markets.api.v1.controller.user.me;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.core.Validator;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public record CreateItemRequestInput(
    String name,
    String bought,
    String itemData
) {

    public static final Validator<CreateItemRequestInput> VALIDATOR = ValidatorBuilder.of(CreateItemRequestInput.class)
        .constraintOnCondition(
            (cond, ctx) -> cond.name != null,
            validate -> validate
                ._string(CreateItemRequestInput::name, "name", c -> c.notBlank().lessThanOrEqual(100))
        )
        ._string(CreateItemRequestInput::bought, "bought", CharSequenceConstraint::uuid)
        .build();

    @Nullable
    public UUID boughtUUID() {
        if (this.bought == null) return null;
        return UUID.fromString(bought);
    }
}
