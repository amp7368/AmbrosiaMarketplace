package com.ambrosia.markets.database.model.item.api;

import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.profile.auction.offer.query.QDAuctionOffer;
import java.util.List;

public interface ItemAuctionApi {

    static List<DAuctionOffer> listOffers(DAuctionItem auctionItem) {
        return new QDAuctionOffer().where()
            .item.eq(auctionItem)
            .findList();
    }
}
