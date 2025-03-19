package com.ambrosia.markets.database.model.message.mail;

import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.trade.transfer.DTransferAction;

public class MailFactory {

    public static void rejectOffer(DAuctionOffer offer) {
        DClient target = offer.getBidder();
        DClient sender = offer.getAuctionItem().getOwner();
    }

    public static void createTransfer(DTransferAction transfer) {

    }
}
