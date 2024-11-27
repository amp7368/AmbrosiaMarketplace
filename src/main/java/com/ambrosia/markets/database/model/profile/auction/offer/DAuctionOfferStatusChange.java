package com.ambrosia.markets.database.model.profile.auction.offer;


import com.ambrosia.markets.database.model.base.BaseEntity;
import io.ebean.annotation.WhenCreated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "auction_offer_status_change")
public class DAuctionOfferStatusChange extends BaseEntity {

    @Id
    protected UUID id;
    @WhenCreated
    @Column(nullable = false)
    protected Instant happenedAt;

    @ManyToOne
    protected DAuctionOffer offer;
    @Column
    protected AuctionOfferStatus newStatus;

}
