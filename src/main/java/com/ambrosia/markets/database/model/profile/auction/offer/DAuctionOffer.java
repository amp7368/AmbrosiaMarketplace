package com.ambrosia.markets.database.model.profile.auction.offer;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.trade.cost.DCost;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "auction_offer")
@UniqueConstraint(columnNames = {"item_id", "bidder_id"})
public class DAuctionOffer extends BaseEntity {

    @Id
    protected UUID id;
    @ManyToOne
    protected DAuctionItem item;

    @ManyToOne(optional = false)
    protected DCost cost;
    @ManyToOne(optional = false)
    protected DClient bidder;
    @Column
    protected Instant completedAt;
    @Column(nullable = false)
    protected AuctionOfferStatus status;
    @OneToMany(fetch = FetchType.LAZY)
    protected List<DAuctionOfferStatusChange> statusHistory = new ArrayList<>();

    public DAuctionOffer(DAuctionItem item, DCost cost, DClient bidder) {
        this.item = item;
        this.cost = cost;
        this.bidder = bidder;
    }

    public void changeStatus(DAuctionOfferStatusChange status, boolean isCompleted) {
        this.status = status.getNewStatus();
        this.statusHistory.add(status);
        if (isCompleted) this.completedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public DAuctionItem getAuction() {
        return item;
    }

    public DItemSnapshot getAuctionItem() {
        return item.getItem();
    }

    public DCost getCost() {
        return cost;
    }

    public DClient getBidder() {
        return bidder;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public AuctionOfferStatus getStatus() {
        return status;
    }
}
