package com.ambrosia.markets.database.model.item;

import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.data.DItemData;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.item.query.QDAuctionItem;
import com.ambrosia.markets.database.model.profile.backpack.DBackpackItem;
import com.ambrosia.markets.database.model.profile.backpack.DClientBackpack;
import com.ambrosia.markets.database.model.profile.backpack.query.QDBackpackItem;
import io.ebean.DB;
import io.ebean.Transaction;
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

    static DItemSnapshot createItem(String name, DClient client, DItemData itemData) {
        DClientBackpack backpack = client.getBackpack();
        DItem item = new DItem();
        DItemSnapshot snapshot = new DItemSnapshot(name, client, item, itemData);
        DBackpackItem backpackItem = new DBackpackItem(backpack, snapshot);
        backpack.addItem(backpackItem);
        try (Transaction transaction = DB.beginTransaction()) {
            item.save(transaction);
            itemData.save(transaction);
            snapshot.save(transaction);
            backpackItem.save(transaction);
            transaction.commit();
        }
        return snapshot;
    }
}
