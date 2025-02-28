package com.ambrosia.markets.api.dto.transfer;

import com.ambrosia.markets.api.base.BaseResponse;
import com.ambrosia.markets.api.dto.item.auction.CostDto;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.trade.transfer.DTransferAction;
import com.ambrosia.markets.database.model.trade.transfer.DTransferType;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class TransferActionDto extends BaseResponse {

    public final UUID id;
    public final DTransferType transferType;
    public final Instant eventDate;
    public final UUID offerId;
    public final UUID sellerId;
    public final CostDto sellerCost;
    public final UUID buyerId;
    public final CostDto buyerCost;

    public TransferActionDto(DTransferAction transfer) {
        this.id = transfer.getId();
        this.transferType = transfer.getTransferType();
        this.eventDate = transfer.getEventDate();
        this.offerId = Optional.ofNullable(transfer.getOffer())
            .map(DAuctionOffer::getId)
            .orElse(null);
        this.sellerId = transfer.getSeller().getId();
        this.sellerCost = new CostDto(transfer.getSellerCost());
        this.buyerId = transfer.getBuyer().getId();
        this.buyerCost = new CostDto(transfer.getBuyerCost());
    }
}
