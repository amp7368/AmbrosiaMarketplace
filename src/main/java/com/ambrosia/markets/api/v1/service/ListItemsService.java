package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.api.base.client.IClientRequest;
import com.ambrosia.markets.api.base.item.ItemSnapshotDto;
import com.ambrosia.markets.api.base.item.ListItemsResponse;
import com.ambrosia.markets.database.model.item.ItemApi;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import java.util.List;

public class ListItemsService {

    public static ListItemsResponse listClientItems(IClientRequest request) {
        int limit = 100000;
        List<DItemSnapshot> items = ItemApi.findBackpackItems(request.getClient(), limit);
        List<ItemSnapshotDto> itemsResponse = ItemSnapshotDto.convert(items);
        return new ListItemsResponse(itemsResponse);
    }

    public static ListItemsResponse listAllItems() {
        int limit = 100000;
        List<DItemSnapshot> items = ItemApi.findBackpackItems(limit);
        List<ItemSnapshotDto> itemsResponse = ItemSnapshotDto.convert(items);
        return new ListItemsResponse(itemsResponse);
    }
}
