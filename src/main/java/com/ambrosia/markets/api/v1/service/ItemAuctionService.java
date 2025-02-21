package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.api.dto.item.auction.AuctionOfferDto;
import com.ambrosia.markets.api.v1.controller.marketplace.items.offers.MakeOfferRequest;
import com.ambrosia.markets.api.v1.controller.user.me.items.auctions.ItemAuctionsUpdateRequest;
import com.ambrosia.markets.api.v1.controller.users.offers.AuctionUpdateStatus;
import com.ambrosia.markets.api.v1.controller.users.offers.AuctionUpdateStatusRequest;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.api.ItemAuctionApi;
import com.ambrosia.markets.database.model.message.mail.MailFactory;
import com.ambrosia.markets.database.model.profile.auction.DClientAuction;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.offer.AuctionOfferStatus;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOfferStatusChange;
import com.ambrosia.markets.database.model.trade.cost.DCost;
import com.ambrosia.markets.database.model.trade.cost.DCostItem;
import com.ambrosia.markets.database.model.trade.cost.DCostItemMisc;
import com.ambrosia.markets.util.emerald.Emeralds;
import io.ebean.DB;
import io.ebean.Transaction;
import io.javalin.http.ConflictResponse;
import java.util.List;

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

    public static DAuctionOffer createOffer(MakeOfferRequest request) {
        DCost cost = new DCost(request.getWillingToPay(), request.isExtended());
        List<DCostItem> items = request.getItems().stream()
            .map(item -> new DCostItem(cost, item))
            .toList();
        List<DCostItemMisc> miscItems = request.getMiscItems().stream()
            .map(req -> new DCostItemMisc(cost, req.getItem(), req.getQuantity()))
            .toList();
        items.forEach(cost::addItem);
        miscItems.forEach(cost::addMiscItem);

        DAuctionOffer offer = new DAuctionOffer(request.getAuctionItem(), cost, request.getBidder());
        DAuctionOfferStatusChange status = new DAuctionOfferStatusChange(offer, AuctionOfferStatus.CURRENT);
        try (Transaction transaction = DB.beginTransaction()) {
            offer.changeStatus(status, false);
            cost.save(transaction);
            items.forEach(e -> e.save(transaction));
            miscItems.forEach(e -> e.save(transaction));
            offer.save(transaction);
            status.save(transaction);
            transaction.commit();
        }
        return offer;
    }

    public static List<AuctionOfferDto> listOffers(DAuctionItem upForSale) {
        List<DAuctionOffer> offers = ItemAuctionApi.listOffers(upForSale);
        return AuctionOfferDto.convert(offers);
    }

    public static List<AuctionOfferDto> listOffers(DClient client) {
        List<DAuctionOffer> offers = ItemAuctionApi.listOffers(client);
        return AuctionOfferDto.convert(offers);
    }

    public static AuctionOfferDto updateStatus(AuctionUpdateStatusRequest request) {
        if (request.newStatus() == AuctionUpdateStatus.REJECTED)
            return rejectOffer(request);
        else throw new IllegalStateException("Unexpected value: " + request.newStatus());
    }

    private static AuctionOfferDto rejectOffer(AuctionUpdateStatusRequest request) {
        DAuctionOffer offer = request.offer();
        DAuctionOfferStatusChange status = new DAuctionOfferStatusChange(offer, AuctionOfferStatus.REJECTED);
        offer.changeStatus(status, true);
        try (Transaction transaction = DB.beginTransaction()) {
            status.save(transaction);
            offer.save(transaction);
            transaction.commit();
        }
        MailFactory.rejectOffer(offer);
        return new AuctionOfferDto(offer);
    }
}
