package com.ambrosia.markets.database.model.trade.cost;

import com.ambrosia.markets.database.model.base.BareBaseEntity;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumns;
import jakarta.persistence.Table;

@Entity
@Table(name = "cost_item")
@PrimaryKeyJoinColumns({
    @PrimaryKeyJoinColumn(name = "cost"),
    @PrimaryKeyJoinColumn(name = "item")
})
public class DCostItem extends BareBaseEntity {

    @ManyToOne
    protected DCost cost;
    @ManyToOne
    protected DItemSnapshot item;

    public DCostItem(DCost cost, DItemSnapshot item) {
        this.cost = cost;
        this.item = item;
    }

    public DCost getCost() {
        return cost;
    }

    public DItemSnapshot getItem() {
        return item;
    }
}
