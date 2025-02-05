package com.ambrosia.markets.api.base.item;

import com.ambrosia.markets.database.wynncraft.item.base.DVersionedItem;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class VersionedItemResponse {

    public final String version;
    public final String name;
    public final String icon;
    public final String itemTier;
    public final String itemType;
    public final ObjectNode identifications;

    public VersionedItemResponse(DVersionedItem data) {
        this.version = data.version();
        this.name = data.name();
        this.icon = data.icon();
        this.itemTier = data.itemTier();
        this.itemType = data.itemType();
        this.identifications = data.identifications();
    }
}
