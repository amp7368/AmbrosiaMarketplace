package com.ambrosia.markets.api.request.miscitem;

import com.ambrosia.markets.database.model.item.stack.DMiscItem;

public class MiscItemRequest {

    public DMiscItem item;
    public int quantity;

    public MiscItemRequest(DMiscItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public DMiscItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
