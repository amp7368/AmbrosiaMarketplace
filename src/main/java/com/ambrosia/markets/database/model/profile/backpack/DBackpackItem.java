package com.ambrosia.markets.database.model.profile.backpack;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "backpack_item")
public class DBackpackItem extends BaseEntity {

    @Id
    protected UUID id;
    @ManyToOne
    protected DClientBackpack backpack;
    @ManyToOne // non-unique
    protected DItemSnapshot item;

    public DBackpackItem(DClientBackpack backpack, DItemSnapshot item) {
        this.backpack = backpack;
        this.item = item;
    }

    public DItemSnapshot getItem() {
        return item;
    }
}
