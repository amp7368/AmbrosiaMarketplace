package com.ambrosia.markets.database.model.item.snapshot;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.base.image.DImage;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.DItem;
import com.ambrosia.markets.database.model.item.data.DItemData;
import com.ambrosia.markets.database.model.item.pricecheck.DPriceCheck;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "item_snapshot")
public class DItemSnapshot extends BaseEntity {

    @Id
    protected UUID id;
    @Column
    protected String name;
    @ManyToOne(optional = false)
    protected DClient owner;
    @ManyToOne
    protected DClient soldTo;
    @Column(nullable = false)
    protected DItemSnapshotStatus status;
    //    @ManyToOne
//    protected DTransferAction selling;
//    @ManyToOne
//    protected DTransferAction bought;
    @ManyToOne(optional = false)
    protected DItem item;
    @ManyToOne(optional = false)
    protected DItemData data;
    @ManyToOne
    protected DImage userImage;
    @OneToMany
    protected List<DPriceCheck> priceChecks = new ArrayList<>();

}
