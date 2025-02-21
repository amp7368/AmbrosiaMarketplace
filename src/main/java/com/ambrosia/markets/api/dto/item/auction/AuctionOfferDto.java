package com.ambrosia.markets.api.dto.item.auction;

import com.ambrosia.markets.database.model.profile.auction.offer.AuctionOfferStatus;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class AuctionOfferDto {

    public final UUID id;
    public final UUID auctionItem;
    public final UUID item;
    public final CostDto cost;
    public final UUID bidder;
    public final Instant completedAt;
    public final AuctionOfferStatus status;

    public AuctionOfferDto(DAuctionOffer offer) {
        this.id = offer.getId();
        this.auctionItem = offer.getAuction().getId();
        this.item = offer.getAuctionItem().getId();
        this.cost = new CostDto(offer.getCost());
        this.bidder = offer.getBidder().getId();
        this.completedAt = offer.getCompletedAt();
        this.status = offer.getStatus();
    }

    public static List<AuctionOfferDto> convert(List<DAuctionOffer> offers) {
        return offers.stream()
            .map(AuctionOfferDto::new)
            .toList();
    }
}
