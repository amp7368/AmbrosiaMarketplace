package com.ambrosia.markets.database.model.trade.transfer;

import com.ambrosia.markets.api.system.exception.ApiExceptions;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.api.ItemApi;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.trade.transfer.query.QDTransferActionPending;
import com.ambrosia.markets.database.system.exception.AlreadySoldException;
import io.ebean.Transaction;
import io.javalin.http.ExpectationFailedResponse;
import org.jspecify.annotations.Nullable;

public interface TransferApi {

    @Nullable
    static DTransferActionPending findMostRecentRequest(DAuctionOffer offer) {
        return new QDTransferActionPending()
            .where().offer.eq(offer)
            .orderBy().eventDate.desc()
            .setMaxRows(1)
            .findOne();
    }

    static void completeTransfer(DTransferAction transfer, Transaction transaction) throws ExpectationFailedResponse {
        DClient buyer = transfer.getBuyer();
        DClient seller = transfer.getSeller();
        for (DItemSnapshot item : transfer.getBuyerCost().getItems()) {
            try {
                ItemApi.transferTo(transfer, item, seller, transaction);
            } catch (AlreadySoldException e) {
                throw ApiExceptions.alreadySold(seller, e);
            }
        }
        for (DItemSnapshot item : transfer.getSellerCost().getItems()) {
            try {
                ItemApi.transferTo(transfer, item, buyer, transaction);
            } catch (AlreadySoldException e) {
                throw ApiExceptions.alreadySold(buyer, e);
            }
        }
    }
}
