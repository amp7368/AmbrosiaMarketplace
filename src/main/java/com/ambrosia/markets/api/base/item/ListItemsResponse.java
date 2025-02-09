package com.ambrosia.markets.api.base.item;

import com.ambrosia.markets.api.base.BaseResponse;
import java.util.List;

public class ListItemsResponse extends BaseResponse {

    public final int itemsCount;
    public final List<ItemSnapshotDto> items;
//    @Nullable
//    public final UUID nextPageCursor = null;
//    public final Integer count = null;

    public ListItemsResponse(List<ItemSnapshotDto> items) {
        this.items = items;
        this.itemsCount = items.size();
    }
}
