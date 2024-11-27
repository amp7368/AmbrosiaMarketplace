package com.ambrosia.markets.database.model.trade.cost;

import com.ambrosia.markets.database.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cost")
public class DCost extends BaseEntity {

    @Id
    protected UUID id;
    @Column(nullable = false)
    protected long emeralds;
    @Column(nullable = false)
    protected boolean isExtended;

    @OneToMany
    protected List<DCostItemMisc> miscItems = new ArrayList<>();
    @OneToMany
    protected List<DCostItem> items = new ArrayList<>();
}
