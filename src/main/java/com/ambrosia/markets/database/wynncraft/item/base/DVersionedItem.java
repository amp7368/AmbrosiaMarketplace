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
        String icon;
        if (this.icon != null) {
            icon = this.icon;
        } else {
            icon = "https://static.wikia.nocookie.net/minecraft_gamepedia/images/0/01/Diamond_Boots_%28item%29_JE3_BE3"
                + ".png/revision/latest?cb=20200226193855";
        }
        return new DVersionedItem(name, icon, itemTier, itemType, identifications.deepCopy(), noriData.deepCopy());
    }
}
