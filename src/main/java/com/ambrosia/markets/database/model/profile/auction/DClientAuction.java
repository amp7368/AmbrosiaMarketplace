package com.ambrosia.markets.database.model.profile.auction;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client_auction")
public class DClientAuction extends BaseEntity {

    @Id
    protected UUID id;
    @JoinColumn
    @OneToOne
    protected DClient client;
    @OneToMany
    protected List<DAuctionItem> onDisplay = new ArrayList<>();

    public DClientAuction(DClient client) {
        this.client = client;
    }
}
