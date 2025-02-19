package com.ambrosia.markets.api.request.miscitem;

import com.ambrosia.markets.database.model.item.api.ItemApi;
import com.ambrosia.markets.database.model.item.stack.DMiscItem;
import io.javalin.http.BadRequestResponse;
import java.util.Objects;
import java.util.UUID;

public class MiscItemInput {

    public String id;
    public Integer quantity;

    public MiscItemRequest toRequest() {
        UUID id = UUID.fromString(this.id);
        int quantity = Objects.requireNonNullElse(this.quantity, 1);
        DMiscItem miscItem = ItemApi.findMiscItem(id);
        if (miscItem == null) throw new BadRequestResponse("miscItem '%s' not found".formatted(id));
        return new MiscItemRequest(miscItem, quantity);
    }
}
