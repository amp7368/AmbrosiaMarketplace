package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.api.dto.item.auction.AuctionOfferDto;
import com.ambrosia.markets.api.v1.controller.marketplace.items.offers.MakeOfferRequest;
import com.ambrosia.markets.api.v1.controller.user.me.items.auctions.ItemAuctionsUpdateRequest;
import com.ambrosia.markets.api.v1.controller.users.offers.status.AuctionUpdateStatusRequest;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.api.ItemAuctionApi;
import com.ambrosia.markets.database.model.profile.auction.DClientAuction;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOfferStatus;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOfferStatusChange;
import com.ambrosia.markets.database.model.trade.cost.DCost;
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
        DAuctionOffer offer;
        try (Transaction transaction = DB.beginTransaction()) {
            DCost cost = CostService.createCost(request.getWillingToPay(), transaction);
            offer = new DAuctionOffer(request.getAuctionItem(), cost, request.getBidder());
            DAuctionOfferStatusChange status = new DAuctionOfferStatusChange(offer, DAuctionOfferStatus.CURRENT);
            offer.changeStatus(status, false);

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
        DAuctionOffer offer = request.offer();
        DAuctionOfferStatus newStatusState = request.newStatus().getNewDStatus();
        assert !offer.isCompleted() : "Offer is already completed!";

        DAuctionOfferStatusChange changeStatusEvent = new DAuctionOfferStatusChange(offer, newStatusState);
        offer.changeStatus(changeStatusEvent, true);
        try (Transaction transaction = DB.beginTransaction()) {
            changeStatusEvent.save(transaction);
            offer.save(transaction);
            transaction.commit();
        }
        return new AuctionOfferDto(offer);
    }
}
