package com.ambrosia.markets.api.item.create;

import com.ambrosia.markets.api.base.BaseResponse;
import com.ambrosia.markets.api.base.item.ItemSnapshotResponse;

public class CreateItemResponse extends BaseResponse {

    public final ItemSnapshotResponse createdItem;

    public CreateItemResponse(ItemSnapshotResponse createdItem) {
        this.createdItem = createdItem;
    }
}
