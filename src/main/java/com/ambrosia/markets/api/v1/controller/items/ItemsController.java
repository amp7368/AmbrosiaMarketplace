package com.ambrosia.markets.api.v1.controller.items;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.dto.item.ItemSnapshotDto;
import com.ambrosia.markets.api.request.item.ItemParam;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import io.javalin.http.Context;

public class ItemsController extends BaseController {

    public void getItem(Context ctx) {
        DItemSnapshot itemSnapshot = ItemParam.parse(ctx.pathParam("item"));
        ItemSnapshotDto item = new ItemSnapshotDto(itemSnapshot);
        ctx.json(new GetItemResponse(item));
    }
}
