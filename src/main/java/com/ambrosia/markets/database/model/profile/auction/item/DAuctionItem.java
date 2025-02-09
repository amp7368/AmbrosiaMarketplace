package com.ambrosia.markets.database.model.profile.auction.item;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.DClientAuction;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.util.emerald.Emeralds;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "auction_item")
// no two auctionItems should exist with the same itemId and endSaleAt = null
public class DAuctionItem extends BaseEntity {

    @Id
    protected UUID id;
    @ManyToOne
    protected DClientAuction auction;

    @ManyToOne // non-unique
    protected DItemSnapshot item;
    @Column // null if not promoted
    protected Integer promotionRank;
    @Column
    protected long listingPrice;

    @Column(nullable = false)
    protected Instant startSaleAt;
    @Column(nullable = false)
    protected DAuctionItemStatus status = DAuctionItemStatus.CURRENT;
    @Column
    protected Instant endSaleAt;

    @OneToMany
    protected List<DAuctionOffer> offers = new ArrayList<>();

    public DAuctionItem(DClientAuction auction, DItemSnapshot item, Emeralds listingPrice, int durationDays, Integer promotionRank) {
        this.auction = auction;
        this.item = item;
        this.listingPrice = listingPrice.amount();
        this.promotionRank = promotionRank;
        this.startSaleAt = Instant.now();
        this.endSaleAt = startSaleAt.plus(durationDays, ChronoUnit.DAYS);
    }

    public Instant getEndSaleAt() {
        return endSaleAt;
    }

    public Instant getStartSaleAt() {
        return startSaleAt;
    }

    public DItemSnapshot getItem() {
        return item;
    }

    public long getListingPrice() {
        return listingPrice;
    }

    public DAuctionItemStatus getStatus() {
        return this.status;
    }
}
