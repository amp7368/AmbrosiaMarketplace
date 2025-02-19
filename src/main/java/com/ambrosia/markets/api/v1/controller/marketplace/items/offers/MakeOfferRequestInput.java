package com.ambrosia.markets.api.v1.controller.marketplace.items.offers;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Constraint;
import am.ik.yavi.core.ConstraintViolationsException;
import am.ik.yavi.core.Validator;
import com.ambrosia.markets.api.request.item.ItemParam;
import com.ambrosia.markets.api.request.miscitem.MiscItemInput;
import com.ambrosia.markets.api.request.miscitem.MiscItemRequest;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.util.emerald.EmeraldParserException;
import com.ambrosia.markets.util.emerald.Emeralds;
import com.ambrosia.markets.util.emerald.EmeraldsParser;
import io.javalin.http.BadRequestResponse;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class MakeOfferRequestInput {

    public static Validator<MakeOfferRequestInput> validator = validator();
    public String emeralds;
    public String[] items;
    public MiscItemInput[] miscItems;

    public static Validator<MakeOfferRequestInput> validator() {
        return ValidatorBuilder.of(MakeOfferRequestInput.class)
            ._objectArray(s -> s.items, "items", Constraint::notNull)
            ._objectArray(s -> s.miscItems, "miscItems", Constraint::notNull)
            ._charSequence(s -> s.emeralds, "emeralds", Constraint::notNull)
            .build();
    }

    @NotNull
    public Emeralds getEmeralds() throws BadRequestResponse {
        try {
            return EmeraldsParser.parse(emeralds);
        } catch (EmeraldParserException e) {
            throw new BadRequestResponse(e.getMessage());
        }
    }

    @NotNull
    public List<DItemSnapshot> getItems() throws ConstraintViolationsException {
        List<DItemSnapshot> items = new ArrayList<>();
        for (String item : this.items) {
            items.add(ItemParam.parse(item));
        }
        return items;
    }

    @NotNull
    public List<MiscItemRequest> getMiscItems() {
        List<MiscItemRequest> items = new ArrayList<>();
        for (MiscItemInput item : this.miscItems) {
            items.add(item.toRequest());
        }
        return items;
    }
}
