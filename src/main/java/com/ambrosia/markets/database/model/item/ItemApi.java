package com.ambrosia.markets.database.model.item;

import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.item.query.QDAuctionItem;
import com.ambrosia.markets.database.model.profile.backpack.DBackpackItem;
import com.ambrosia.markets.database.model.profile.backpack.query.QDBackpackItem;
import java.util.List;
import java.util.stream.Stream;
import org.jspecify.annotations.Nullable;

public interface ItemApi {

    static List<DItemSnapshot> findBackpackItems(DClient client, int limit) {
        Stream<DBackpackItem> backpackItems = new QDBackpackItem().fetch("item")
            .where().backpack.eq(client.getBackpack())
            .orderBy().item.createdAt.desc()
            .setMaxRows(limit)
            .findStream();
        return backpackItems
            .map(DBackpackItem::getItem)
            .toList();
    }

    static List<DItemSnapshot> findBackpackItems(int limit) {
        Stream<DBackpackItem> backpackItems = new QDBackpackItem().fetch("item")
            .orderBy().item.createdAt.desc()
            .setMaxRows(limit)
            .findStream();
        return backpackItems
            .map(DBackpackItem::getItem)
            .toList();
    }

    @Nullable
    static DAuctionItem findCurrentAuctionItem(DItemSnapshot item) {
        return new QDAuctionItem().where()
            .item.eq(item)
            .endSaleAt.isNull()
            .findOne();
    }
}
