package com.ambrosia.markets.database.model.item.api;

import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.DItem;
import com.ambrosia.markets.database.model.item.data.DItemData;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.item.snapshot.query.QDItemSnapshot;
import com.ambrosia.markets.database.model.item.stack.DMiscItem;
import com.ambrosia.markets.database.model.item.stack.query.QDMiscItem;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItemStatus;
import com.ambrosia.markets.database.model.profile.auction.item.query.QDAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.profile.auction.offer.query.QDAuctionOffer;
import com.ambrosia.markets.database.model.profile.backpack.DBackpackItem;
import com.ambrosia.markets.database.model.profile.backpack.DClientBackpack;
import io.ebean.DB;
import io.ebean.Transaction;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.jspecify.annotations.Nullable;

public interface ItemApi {

    static List<DItemSnapshot> findClientItems(FindPaginatedItems req, DClient client) {
        return new QDItemSnapshot()
            .where().owner.eq(client)
            .orderBy().createdAt.desc()
            .setMaxRows(req.limit())
            .findList();
    }

    static List<DItemSnapshot> findItems(FindPaginatedItems req) {
        return new QDItemSnapshot()
            .orderBy().createdAt.desc()
            .setMaxRows(req.limit())
            .findList();
    }

    @Nullable
    static DAuctionItem findCurrentAuctionItem(DItemSnapshot item) {
        return new QDAuctionItem().where()
            .item.eq(item)
            .status.eq(DAuctionItemStatus.CURRENT)
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

    @Nullable
    static DItemSnapshot findItem(UUID id) {
        return new QDItemSnapshot().where()
            .id.eq(id)
            .findOne();
    }

    static List<DItemSnapshot> findAuctionItems(FindPaginatedItems req) {
        return new QDItemSnapshot().where()
            .auctionItems.status.eq(DAuctionItemStatus.CURRENT)
            .auctionItems.endSaleAt.after(Instant.now())
            .orderBy().auctionItems.startSaleAt.desc()
            .setMaxRows(req.limit())
            .findList();
    }

    @Nullable
    static DMiscItem findMiscItem(UUID id) {
        return new QDMiscItem()
            .where().id.eq(id)
            .findOne();
    }

    @Nullable
    static DAuctionOffer findOffers(DAuctionItem upForSale, DClient bidder) {
        return new QDAuctionOffer().where()
            .item.eq(upForSale)
            .bidder.eq(bidder)
            .findOne();
    }
}
