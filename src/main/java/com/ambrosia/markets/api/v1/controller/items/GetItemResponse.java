package com.ambrosia.markets.api.v1.controller.items;

import com.ambrosia.markets.api.base.BaseResponse;
import com.ambrosia.markets.api.dto.item.ItemSnapshotDto;

public class GetItemResponse extends BaseResponse {

    public final ItemSnapshotDto item;

    public GetItemResponse(ItemSnapshotDto item) {
        this.item = item;
    }
}
