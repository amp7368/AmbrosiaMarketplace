package com.ambrosia.markets.database.model.trade.cost;

import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import io.ebean.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumns;
import jakarta.persistence.Table;

@Entity
@Table(name = "cost_item")
@PrimaryKeyJoinColumns({
    @PrimaryKeyJoinColumn(name = "cost"),
    @PrimaryKeyJoinColumn(name = "item")
})
public class DCostItem extends Model {

    @ManyToOne
    protected DCost cost;
    @JoinColumn
    @OneToOne
    protected DItemSnapshot item;

}
