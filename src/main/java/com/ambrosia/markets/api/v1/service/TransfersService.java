package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.api.v1.controller.transfers.confirm.ConfirmTransferRequest;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.trade.cost.DCost;
import com.ambrosia.markets.database.model.trade.transfer.DTransferAction;
import com.ambrosia.markets.database.model.trade.transfer.DTransferType;
import io.ebean.DB;
import io.ebean.Transaction;
import java.time.Instant;

public class TransfersService {

    public static DTransferAction createTransfer(ConfirmTransferRequest request) {
        Instant eventDate = Instant.now();
        DAuctionOffer offer = request.getAuction();
        DClient seller = offer.getSeller();
        DClient buyer = offer.getBidder();
        try (Transaction transaction = DB.beginTransaction()) {
            DCost sellerCost = CostService.createCost(request.getSellerCost(), transaction);
            DCost buyerCost = CostService.createCost(request.getBuyerCost(), transaction);

            DTransferAction transfer = new DTransferAction(
                eventDate, DTransferType.TRADE, offer,
                seller, sellerCost, buyer, buyerCost
            );
            transfer.save(transaction);
            transaction.commit();
            return transfer;
        }
    }
}
