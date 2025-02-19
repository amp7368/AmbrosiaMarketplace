package com.ambrosia.markets.api.request.item;

import am.ik.yavi.arguments.StringValidator;
import am.ik.yavi.builder.StringValidatorBuilder;
import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.ApplicativeValidator;
import am.ik.yavi.core.Constraint;
import am.ik.yavi.core.ConstraintViolationsException;
import am.ik.yavi.core.ValueValidator;
import com.ambrosia.markets.database.model.item.api.ItemApi;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import io.javalin.http.BadRequestResponse;
import java.util.UUID;
import java.util.function.Supplier;

public class ItemParam {


    public static ValueValidator<String, DItemSnapshot> validator = validator();
    private final DItemSnapshot item;

    // todo delete
    public ItemParam(String itemIdInput) {
        try {
            UUID itemId = UUID.fromString(itemIdInput);
            item = ItemApi.findItem(itemId);
            if (item == null)
                throw new BadRequestResponse("Item id of '" + itemId + "' not found");
        } catch (IllegalArgumentException e) {
            throw new BadRequestResponse("Item query param. Please provide a valid UUID");
        }
    }

    private static ValueValidator<String, DItemSnapshot> validator() {
        StringValidator<UUID> base = StringValidatorBuilder.of("item", arg -> arg.notNull().uuid()).build(a -> {
            try {
                return UUID.fromString(a);
            } catch (IllegalArgumentException e) {
                return null;
            }
        });

        ApplicativeValidator<Supplier<UUID>> uuidCheck = ValidatorBuilder.<Supplier<UUID>>of()
            ._object(uuidSupplier -> {
                return uuidSupplier.get();
            }, "", Constraint::notNull)
            .build()
            .applicative();

        ApplicativeValidator<Supplier<DItemSnapshot>> finalValidate = ValidatorBuilder.<Supplier<DItemSnapshot>>of()
            ._object(uuidSupplier -> {
                return uuidSupplier.get();
            }, "", Constraint::notNull)
            .build()
            .applicative();

        return base.lazy().andThen(uuidCheck)
            .andThen(Supplier::get)
            .andThen(s -> (Supplier<DItemSnapshot>) () -> ItemApi.findItem(s))
            .andThen(finalValidate)
            .andThen(Supplier::get);
    }

    public static DItemSnapshot parse(String item) throws ConstraintViolationsException {
        return validator.validated(item);
    }

    public DItemSnapshot getItem() {
        return item;
    }
}
