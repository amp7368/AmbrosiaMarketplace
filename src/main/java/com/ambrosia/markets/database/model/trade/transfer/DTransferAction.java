package com.ambrosia.markets.database.model.trade.transfer;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.trade.cost.DCost;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import org.jspecify.annotations.Nullable;


// transfer includes many items in a trade
@Entity
@Table(name = "transfer_action")
public class DTransferAction extends BaseEntity {

    @Id
    protected UUID id;
    @Column(nullable = false)
    protected DTransferType transferType;
    @Column(nullable = false)
    protected Instant eventDate;

    @ManyToOne
    protected DAuctionOffer offer;

    // whoever initiated the transfer
    @ManyToOne(optional = false)
    protected DClient seller;
    @JoinColumn
    @OneToOne(optional = false)
    protected DCost sellerCost;

    // whoever accepted the transfer
    @ManyToOne(optional = false)
    protected DClient buyer;
    @JoinColumn
    @OneToOne(optional = false)
    protected DCost buyerCost;

    public DTransferAction(
        Instant eventDate,
        DTransferType type,
        DAuctionOffer offer,
        DClient seller,
        DCost sellerCost,
        DClient buyer,
        DCost buyerCost
    ) {
        this.eventDate = eventDate;
        this.transferType = type;
        this.offer = offer;
        this.seller = seller;
        this.sellerCost = sellerCost;
        this.buyer = buyer;
        this.buyerCost = buyerCost;
    }

    public UUID getId() {
        return id;
    }

    public DTransferType getTransferType() {
        return transferType;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    @Nullable
    public DAuctionOffer getOffer() {
        return offer;
    }

    public DClient getSeller() {
        return seller;
    }

    public DCost getSellerCost() {
        return sellerCost;
    }

    public DClient getBuyer() {
        return buyer;
    }

    public DCost getBuyerCost() {
        return buyerCost;
    }
}

