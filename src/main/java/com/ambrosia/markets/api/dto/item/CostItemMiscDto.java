package com.ambrosia.markets.api.dto.item;

import com.ambrosia.markets.database.model.item.stack.DMiscItem;
import com.ambrosia.markets.database.model.trade.cost.DCostItemMisc;
import java.util.List;
import java.util.UUID;

public class CostItemMiscDto {

    public final UUID id;
    public final String name;
    public final String description;
    public final int quantity;

    public CostItemMiscDto(DCostItemMisc miscItem) {
        DMiscItem item = miscItem.getItem();
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.quantity = miscItem.getQuantity();
    }

    public static List<CostItemMiscDto> convert(List<DCostItemMisc> miscItems) {
        return miscItems.stream()
            .map(CostItemMiscDto::new)
            .toList();
    }
}
