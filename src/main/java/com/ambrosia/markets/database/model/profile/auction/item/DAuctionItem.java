package com.ambrosia.markets.database.model.profile.auction.item;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.profile.auction.DClientAuction;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import io.ebean.annotation.WhenCreated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
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

    @WhenCreated
    protected Instant startSaleAt;
    @Column
    protected Instant endSaleAt;

    @OneToMany
    protected List<DAuctionOffer> offers = new ArrayList<>();

    public DAuctionItem() {
    }

    public Instant getEndSaleAt() {
        return endSaleAt;
    }

    public Instant getStartSaleAt() {
        return startSaleAt;
    }
}
