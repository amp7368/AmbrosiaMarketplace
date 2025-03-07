package com.ambrosia.markets.api.v1.controller.user.me.items;

import com.ambrosia.markets.api.base.client.BaseClientAuthorizationRequest;
import io.javalin.http.Context;
import java.util.UUID;

public class CreateItemRequest extends BaseClientAuthorizationRequest {

    private final String name;
    private final UUID bought;
    private final String itemData;

    public CreateItemRequest(Context ctx, CreateItemRequestInput input) {
        super(ctx);
        this.name = input.name();
        this.bought = input.boughtUUID();
        this.itemData = input.itemData();
    }

    public String getName() {
        return name;
    }

    public UUID getBought() {
        return bought;
    }

    public String getItemData() {
        return itemData;
    }
}

