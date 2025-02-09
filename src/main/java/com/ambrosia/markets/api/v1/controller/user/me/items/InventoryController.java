package com.ambrosia.markets.api.v1.controller.user.me.items;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.client.BaseClientAuthorizationRequest;
import com.ambrosia.markets.api.base.item.ItemSnapshotDto;
import com.ambrosia.markets.api.base.item.ListItemsResponse;
import com.ambrosia.markets.api.upstream.UpstreamException;
import com.ambrosia.markets.api.upstream.nori.item_analysis.NoriItemAnalysis;
import com.ambrosia.markets.api.v1.service.ListItemsService;
import com.ambrosia.markets.database.model.item.ItemApi;
import com.ambrosia.markets.database.model.item.data.DItemData;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import io.javalin.http.Context;
import io.javalin.http.InternalServerErrorResponse;
import java.util.concurrent.ExecutionException;
import org.jetbrains.annotations.NotNull;

public class InventoryController extends BaseController {

    public void createItem(@NotNull Context ctx) throws Exception {
        CreateItemRequestInput input = validateBody(ctx, CreateItemRequestInput.VALIDATOR, CreateItemRequestInput.class);
        CreateItemRequest request = new CreateItemRequest(ctx, input);

        ctx.async(() -> createItemAsync(ctx, request));
    }

    private void createItemAsync(@NotNull Context ctx, CreateItemRequest request) throws UpstreamException {
        DItemData itemData;
        try {
            itemData = NoriItemAnalysis.requestItemAnalysisNow(request.getItemData());
        } catch (ExecutionException e) {
            if (e.getCause() instanceof UpstreamException cause) throw cause;
            throw new InternalServerErrorResponse("Failed to analyze item");
        }

        DItemSnapshot snapshot = ItemApi.createItem(request.getName(), request.getClient(), itemData);

        ItemSnapshotDto createdItem = new ItemSnapshotDto(snapshot);
        CreateItemResponse response = new CreateItemResponse(createdItem);
        ctx.json(response);
    }

    public void listItems(@NotNull Context ctx) throws Exception {
        BaseClientAuthorizationRequest request = new BaseClientAuthorizationRequest(ctx);
        ListItemsResponse response = ListItemsService.listClientItems(request);
        ctx.json(response);
    }
}
