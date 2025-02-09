package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.api.base.client.IClientRequest;
import com.ambrosia.markets.api.base.item.ItemSnapshotDto;
import com.ambrosia.markets.api.base.item.ListItemsResponse;
import com.ambrosia.markets.database.model.item.api.FindPaginatedItems;
import com.ambrosia.markets.database.model.item.api.ItemApi;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import java.util.List;

public class ListItemsService {

    public static ListItemsResponse listClientItems(IClientRequest request) {
        FindPaginatedItems req = new FindPaginatedItems(100000);
        List<DItemSnapshot> items = ItemApi.findBackpackItems(req, request.getClient());
        List<ItemSnapshotDto> itemsResponse = ItemSnapshotDto.convert(items);
        return new ListItemsResponse(itemsResponse);
    }

    public static ListItemsResponse listMarketplaceItems() {
        FindPaginatedItems req = new FindPaginatedItems(100000);
        List<DItemSnapshot> items = ItemApi.findAuctionItems(req);
        List<ItemSnapshotDto> itemsResponse = ItemSnapshotDto.convert(items);
        return new ListItemsResponse(itemsResponse);
    }
}
