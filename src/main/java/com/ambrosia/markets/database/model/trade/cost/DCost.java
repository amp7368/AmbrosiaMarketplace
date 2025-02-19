package com.ambrosia.markets.database.model.trade.cost;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.util.emerald.Emeralds;
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

    public DCost(Emeralds emeralds, boolean isExtended) {
        this.emeralds = emeralds.amount();
        this.isExtended = isExtended;
    }

    public void addMiscItem(DCostItemMisc miscItem) {
        this.miscItems.add(miscItem);
    }

    public void addItem(DCostItem item) {
        this.items.add(item);
    }

    public UUID getId() {
        return id;
    }

    public Emeralds getEmeralds() {
        return Emeralds.of(emeralds);
    }

    public long getEmeraldsAmount() {
        return emeralds;
    }

    public boolean isExtended() {
        return isExtended;
    }

    public List<DCostItemMisc> getMiscItems() {
        return miscItems;
    }

    public List<DItemSnapshot> getItems() {
        return items.stream()
            .map(DCostItem::getItem)
            .toList();
    }
}
