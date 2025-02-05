package com.ambrosia.markets.api.upstream.nori.item_analysis;

import com.ambrosia.markets.api.upstream.UpstreamException;
import com.ambrosia.markets.api.upstream.nori.NoriRateLimit;
import com.ambrosia.markets.database.model.item.data.DItemData;
import com.ambrosia.markets.database.wynncraft.item.base.DVersionedItem;
import com.ambrosia.markets.discord.system.log.DiscordLog;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

public class NoriItemAnalysis {

    public static CompletableFuture<DItemData> requestItemAnalysis(String encodedItem) {
        return NoriRateLimit.itemAnalysis(() -> createRequest(encodedItem))
            .thenApply(res -> handle(encodedItem, res));
    }

    public static DItemData requestItemAnalysisNow(String encodedItem) throws ExecutionException {
        try {
            return requestItemAnalysis(encodedItem).get();
        } catch (ExecutionException e) {
            throw e;
        } catch (InterruptedException e) {
            String msg = "error with %s encodedItem request".formatted(encodedItem);
            DiscordLog.errorSystem(msg, e);
            throw new RuntimeException(e);
        }
    }

    private static DItemData handle(String encodedItem, Response res) throws CompletionException {
        if (!res.isSuccessful()) {
            throw new CompletionException(new UpstreamException(res.code()));
        }
        ResponseBody body = res.body();
        JsonNode response;
        try {
            response = new ObjectMapper().readTree(body.byteStream());
        } catch (IOException e) {
            throw new CompletionException(e);
        }
        JsonNode result = response.get("Result");

        String icon = result.get("icon").asText();
        String itemTier = result.get("item_tier").asText();
        String itemType = result.get("item_type").asText();

        String name = result.get("internalName").asText();
        ObjectNode identifications = (ObjectNode) result.get(name);

        ObjectNode noriData = new ObjectMapper().createObjectNode();
        Iterator<String> iter = result.fieldNames();
        while (iter.hasNext()) {
            String key = iter.next();
            if (!Set.of("icon", "item_tier", "item_type", "internalName", name).contains(key)) {
                noriData.set(key, result.get(key));
            }
        }

        DVersionedItem item = new DVersionedItem(name, icon, itemTier, itemType, identifications, noriData);
        return new DItemData(encodedItem, item);
    }


    @NotNull
    private static Request createRequest(String encodedItem) {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("encoded_item", encodedItem);
        byte[] requestBytes = requestJson.toString().getBytes(StandardCharsets.UTF_8);
        RequestBody requestBody = RequestBody.create(requestBytes);

        return new Builder()
            .url("https://nori.fish/api/item/analysis")
            .post(requestBody)
            .build();
    }
}
