package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.api.v1.controller.user.me.items.auctions.ItemAuctionsUpdateRequest;
import com.ambrosia.markets.database.model.profile.auction.DClientAuction;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.util.emerald.Emeralds;
import io.ebean.DB;
import io.ebean.Transaction;
import io.javalin.http.ConflictResponse;

public class ItemAuctionService {

    public static DAuctionItem markForAuction(ItemAuctionsUpdateRequest request) {
        DClientAuction clientAuction = request.getClient().getAuction();
        Emeralds listedPrice = request.getListedPrice();

        int durationDays = request.getDurationDays();
        DAuctionItem auctionItem = new DAuctionItem(clientAuction, request.getItem(), listedPrice, durationDays, null);
        try (Transaction transaction = DB.beginTransaction()) {
            if (request.getItem().getCurrentAuction() != null) {
                throw new ConflictResponse("Item is already up for sale");
            }
            auctionItem.save(transaction);
            transaction.commit();
        }
        return auctionItem;
    }
}
