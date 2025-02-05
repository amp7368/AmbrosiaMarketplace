package com.ambrosia.markets.api.item.create;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.item.ItemSnapshotResponse;
import com.ambrosia.markets.api.upstream.UpstreamException;
import com.ambrosia.markets.api.upstream.nori.item_analysis.NoriItemAnalysis;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.DItem;
import com.ambrosia.markets.database.model.item.data.DItemData;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.backpack.DBackpackItem;
import com.ambrosia.markets.database.model.profile.backpack.DClientBackpack;
import io.ebean.DB;
import io.ebean.Transaction;
import io.javalin.http.Context;
import io.javalin.http.InternalServerErrorResponse;
import java.util.concurrent.ExecutionException;
import org.jetbrains.annotations.NotNull;

public class CreateItemController extends BaseController {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        CreateItemRequestInput input = validateBody(ctx, CreateItemRequestInput.VALIDATOR, CreateItemRequestInput.class);
        CreateItemRequest request = new CreateItemRequest(ctx, input);

        ctx.async(() -> handleAsync(ctx, request));
    }

    public void handleAsync(@NotNull Context ctx, CreateItemRequest request) throws UpstreamException {
        DItemData itemData;
        try {
            itemData = NoriItemAnalysis.requestItemAnalysisNow(request.getItemData());
        } catch (ExecutionException e) {
            if (e.getCause() instanceof UpstreamException cause) throw cause;
            throw new InternalServerErrorResponse("Failed to analyze item");
        }
        DClient client = request.getClient();
        DClientBackpack backpack = client.getBackpack();

        DItem item = new DItem();
        DItemSnapshot snapshot = new DItemSnapshot(request.getName(), client, item, itemData);
        DBackpackItem backpackItem = new DBackpackItem(backpack, snapshot);
        backpack.addItem(backpackItem);
        try (Transaction transaction = DB.beginTransaction()) {
            item.save(transaction);
            itemData.save(transaction);
            snapshot.save(transaction);
            backpackItem.save(transaction);
            transaction.commit();
        }
        ItemSnapshotResponse createdItem = new ItemSnapshotResponse(snapshot);
        CreateItemResponse response = new CreateItemResponse(createdItem);
        ctx.json(response);
    }
}
