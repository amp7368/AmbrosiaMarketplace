package com.ambrosia.markets.database.model.item.api;

import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.profile.auction.offer.query.QDAuctionOffer;
import java.util.List;
import java.util.UUID;
import org.jspecify.annotations.Nullable;

public interface ItemAuctionApi {

    static List<DAuctionOffer> listOffers(DAuctionItem auctionItem) {
        return new QDAuctionOffer().where()
            .item.eq(auctionItem)
            .orderBy().modifiedAt.desc()
            .findList();
    }

    static List<DAuctionOffer> listOffers(DClient client) {
        return new QDAuctionOffer().where()
            .bidder.eq(client)
            .orderBy().modifiedAt.desc()
            .findList();
    }

    @Nullable
    static DAuctionOffer findOffer(UUID id) {
        return new QDAuctionOffer().where()
            .id.eq(id)
            .findOne();
    }
}
