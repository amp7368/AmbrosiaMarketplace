package com.ambrosia.markets.api.v1.controller.user.me.items;

import com.ambrosia.markets.api.base.BaseResponse;
import com.ambrosia.markets.api.base.item.ItemSnapshotDto;

public class CreateItemResponse extends BaseResponse {

    public final ItemSnapshotDto createdItem;

    public CreateItemResponse(ItemSnapshotDto createdItem) {
        this.createdItem = createdItem;
    }
}
