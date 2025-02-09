package com.ambrosia.markets.api.base.item;

import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class ItemSnapshotDto {

    public final String displayName;
    public final UUID id;
    public final UUID owner;
    public final VersionedItemResponse data;
    public final String encodedString;

    @Nullable
    public final Instant startSaleAt;

    public ItemSnapshotDto(DItemSnapshot item) {
        id = item.getId();
        displayName = item.getName();
        owner = item.getOwner().getId();
        data = new VersionedItemResponse(item.getData());
        encodedString = item.getEncodedString();
        DAuctionItem auction = item.getCurrentAuction();
        startSaleAt = Optional.ofNullable(auction)
            .map(DAuctionItem::getStartSaleAt)
            .orElse(null);
    }

    public static List<ItemSnapshotDto> convert(List<DItemSnapshot> items) {
        return items.parallelStream()
            .map(ItemSnapshotDto::new)
            .toList();
    }
}
