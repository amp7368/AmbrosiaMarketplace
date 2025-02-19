package com.ambrosia.markets.database.model.trade.cost;

import com.ambrosia.markets.database.model.base.BareBaseEntity;
import com.ambrosia.markets.database.model.item.stack.DMiscItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "cost_misc_item")
public class DCostItemMisc extends BareBaseEntity {

    @Id
    protected UUID id;
    @ManyToOne
    protected DCost cost;
    @ManyToOne
    protected DMiscItem item;

    @Column(nullable = false)
    protected int quantity;

    public DCostItemMisc(DCost cost, DMiscItem item, int quantity) {
        this.cost = cost;
        this.item = item;
        this.quantity = quantity;
    }

    public DCost getCost() {
        return cost;
    }

    public DMiscItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
