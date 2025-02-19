package com.ambrosia.markets.api.dto.item;

import com.ambrosia.markets.api.dto.item.auction.ItemSnapshotAuctionDto;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class ItemSnapshotDto {

    public final String displayName;
    public final UUID id;
    public final UUID owner;
    public final VersionedItemResponse data;
    public final String encodedString;
    @Nullable
    public final ItemSnapshotAuctionDto auction;


    public ItemSnapshotDto(DItemSnapshot item) {
        id = item.getId();
        displayName = item.getName();
        owner = item.getOwner().getId();
        data = new VersionedItemResponse(item.getData());
        encodedString = item.getEncodedString();
        DAuctionItem itemAuction = item.getCurrentAuction();
        this.auction = itemAuction == null ? null : new ItemSnapshotAuctionDto(itemAuction);
    }

    public static List<ItemSnapshotDto> convert(List<DItemSnapshot> items) {
        return items.parallelStream()
            .map(ItemSnapshotDto::new)
            .toList();
    }
}
