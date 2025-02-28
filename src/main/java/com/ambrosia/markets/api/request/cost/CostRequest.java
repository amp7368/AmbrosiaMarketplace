package com.ambrosia.markets.api.request.cost;

import com.ambrosia.markets.api.request.miscitem.MiscItemRequest;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.util.emerald.Emeralds;
import io.javalin.http.HttpResponseException;
import java.util.List;

public class CostRequest {

    private final Emeralds emeralds;
    private final List<DItemSnapshot> items;
    private final List<MiscItemRequest> miscItems;

    public CostRequest(CostInput input) throws HttpResponseException {
        this.emeralds = input.getEmeralds();
        this.items = input.getItems();
        this.miscItems = input.getMiscItems();
    }

    public Emeralds getEmeralds() {
        return emeralds;
    }

    public List<DItemSnapshot> getItems() {
        return items;
    }

    public List<MiscItemRequest> getMiscItems() {
        return miscItems;
    }

    public boolean isExtended() {
        return !items.isEmpty() || !miscItems.isEmpty();
    }
}
