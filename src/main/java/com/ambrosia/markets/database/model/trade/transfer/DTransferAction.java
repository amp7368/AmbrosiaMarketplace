package com.ambrosia.markets.database.model.trade.transfer;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
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

    // whoever initiated the transfer
    @ManyToOne(optional = false)
    protected DClient alice;
    @JoinColumn
    @OneToOne(optional = false)
    protected DCost aliceOffer;

    // whoever accepted the transfer
    @ManyToOne
    protected DClient bob;
    @JoinColumn
    @OneToOne
    protected DCost bobOffer;
}

