package com.ambrosia.markets.database.model.profile.auction.offer;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.trade.cost.DCost;
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
@Table(name = "auction_offer")
public class DAuctionOffer extends BaseEntity {

    @Id
    protected UUID id;
    @ManyToOne
    protected DAuctionItem item;

    @Column(nullable = false)
    protected DCost cost;
    @ManyToOne(optional = false)
    protected DClient bidder;
    @Column
    protected Instant completedAt;
    @Column(nullable = false)
    protected AuctionOfferStatus seller;
    @OneToMany
    protected List<DAuctionOfferStatusChange> statusHistory = new ArrayList<>();
}
