package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.api.system.exception.ApiExceptions;
import com.ambrosia.markets.api.v1.controller.transfers.confirm.ConfirmTransferRequest;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.message.mail.MailFactory;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.trade.cost.DCost;
import com.ambrosia.markets.database.model.trade.transfer.DTransferAction;
import com.ambrosia.markets.database.model.trade.transfer.DTransferActionPending;
import com.ambrosia.markets.database.model.trade.transfer.DTransferType;
import com.ambrosia.markets.database.model.trade.transfer.TransferApi;
import com.ambrosia.markets.database.system.exception.AlreadySoldException;
import io.ebean.DB;
import io.ebean.Transaction;
import io.javalin.http.ExpectationFailedResponse;
import java.time.Instant;

public class TransferService {

    public static DTransferActionPending confirmTransferRequest(ConfirmTransferRequest request) throws ExpectationFailedResponse {
        DTransferActionPending requestToConfirm = TransferApi.findMostRecentRequest(request.getAuctionOffer());
        if (requestToConfirm == null) {
            return createTransferRequest(Instant.now(), request);
        }
        boolean isConfirmed = request.isEqual(requestToConfirm);
        if (!isConfirmed) {
            return createTransferRequest(requestToConfirm.getEventDate(), request);
        }

        if (request.isBuyerRequested()) {
            requestToConfirm.setBuyerConfirmed();
        } else if (request.isSellerRequested()) {
            requestToConfirm.setSellerConfirmed();
        }
        boolean confirmed = requestToConfirm.isConfirmed();
        if (!confirmed) return requestToConfirm;

        DTransferAction transfer;
        try (Transaction transaction = DB.beginTransaction()) {
            requestToConfirm.save(transaction);
            transfer = createTransfer(requestToConfirm, transaction);
            transaction.commit();
        }
        MailFactory.createTransfer(transfer);
        return requestToConfirm;
    }

    private static DTransferAction createTransfer(DTransferActionPending request, Transaction transaction)
        throws ExpectationFailedResponse {
        DTransferAction transfer = new DTransferAction(request);
        TransferApi.completeTransfer(transfer, transaction);
        transfer.save(transaction);
        return transfer;
    }

    private static DTransferActionPending createTransferRequest(Instant eventDate, ConfirmTransferRequest request)
        throws ExpectationFailedResponse {
        DAuctionOffer offer = request.getAuctionOffer();
        DClient seller = offer.getSeller();
        DClient buyer = offer.getBidder();
        try (Transaction transaction = DB.beginTransaction()) {
            DCost sellerCost = CostService.createCost(request.getSellerCost(), transaction);
            DCost buyerCost = CostService.createCost(request.getBuyerCost(), transaction);

            DTransferActionPending transfer = new DTransferActionPending(
                eventDate, DTransferType.TRADE, offer,
                seller, sellerCost, buyer, buyerCost
            );
            transfer.save(transaction);
            transaction.commit();
            return transfer;
        } catch (AlreadySoldException e) {
            throw ApiExceptions.cannotOffer(e);
        }
    }
}
