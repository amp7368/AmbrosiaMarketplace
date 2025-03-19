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

    @Override
    public int hashCode() {
        return item.getId().hashCode() + quantity;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MiscItemRequest other &&
            item.getId().equals(other.item.getId()) &&
            quantity == other.quantity;
    }
}
