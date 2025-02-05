package com.ambrosia.markets.database.wynncraft.item.base;

import com.fasterxml.jackson.databind.node.ObjectNode;

public record DVersionedItem(
    String version,
    String name,
    String icon,
    String itemTier,
    String itemType,
    ObjectNode identifications,
    ObjectNode noriData
) {

    public DVersionedItem(
        String name,
        String icon,
        String itemTier,
        String itemType,
        ObjectNode identifications,
        ObjectNode noriData
    ) {
        this(VersionedItemTypeId.VERSION_1_0, name, icon, itemTier, itemType, identifications, noriData);
    }

    public DVersionedItem deepCopy() {
        return new DVersionedItem(name, icon, itemTier, itemType, identifications.deepCopy(), noriData.deepCopy());
    }
}
