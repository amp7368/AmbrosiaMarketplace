package com.ambrosia.markets.api.dto.item.auction;

import com.ambrosia.markets.api.dto.item.CostItemMiscDto;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.trade.cost.DCost;
import java.util.List;
import java.util.UUID;

public class CostDto {

    public final long rawEmeralds;
    public final List<CostItemMiscDto> miscItems;
    public final List<UUID> items;

    public CostDto(DCost cost) {
        this.rawEmeralds = cost.getEmeraldsAmount();
        this.miscItems = CostItemMiscDto.convert(cost.getMiscItems());
        this.items = cost.getItems().stream()
            .map(DItemSnapshot::getId)
            .toList();
    }
}
