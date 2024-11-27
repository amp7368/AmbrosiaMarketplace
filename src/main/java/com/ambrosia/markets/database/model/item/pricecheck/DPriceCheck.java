package com.ambrosia.markets.database.model.item.pricecheck;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "price_check")
public class DPriceCheck extends BaseEntity {

    @Id
    protected UUID id;
    @ManyToOne
    protected DClient whoChecked;
    @Column
    protected long emeralds;
    @ManyToOne
    protected DItemSnapshot item;

}
