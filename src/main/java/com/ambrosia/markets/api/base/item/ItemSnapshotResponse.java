package com.ambrosia.markets.api.base.item;

import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import java.util.UUID;

public class ItemSnapshotResponse {

    public final String displayName;
    public final UUID id;
    public final UUID owner;
    public final VersionedItemResponse data;
    public final String encodedString;


    public ItemSnapshotResponse(DItemSnapshot item) {
        id = item.getId();
        displayName = item.getName();
        owner = item.getOwner().getId();
        data = new VersionedItemResponse(item.getData());
        encodedString = item.getEncodedString();
    }
}
