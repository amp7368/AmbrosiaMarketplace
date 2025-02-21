package com.ambrosia.markets.database.model.message.mail;

import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;

public class MailFactory {

    public static void rejectOffer(DAuctionOffer offer) {
        DClient target = offer.getBidder();
        DClient sender = offer.getAuctionItem().getOwner();
    }
}
