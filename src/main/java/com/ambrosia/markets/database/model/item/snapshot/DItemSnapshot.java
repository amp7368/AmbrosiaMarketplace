package com.ambrosia.markets.database.model.item.snapshot;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.base.image.DImage;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.item.DItem;
import com.ambrosia.markets.database.model.item.api.ItemApi;
import com.ambrosia.markets.database.model.item.data.DItemData;
import com.ambrosia.markets.database.model.item.pricecheck.DPriceCheck;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.backpack.DBackpackItem;
import com.ambrosia.markets.database.model.trade.transfer.DTransferAction;
import com.ambrosia.markets.database.wynncraft.item.base.DVersionedItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @ManyToOne
    protected DTransferAction selling;
    @ManyToOne
    protected DTransferAction bought;
    @ManyToOne(optional = false)
    protected DItem item;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    protected DItemData data; // encoded string
    @ManyToOne
    protected DImage userImage;
    @OneToMany(fetch = FetchType.LAZY)
    protected List<DPriceCheck> priceChecks = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    protected List<DAuctionItem> auctionItems = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    protected List<DBackpackItem> backpackItems = new ArrayList<>();

    public DItemSnapshot(String name, DClient owner, DItem item, DItemData data) {
        this.name = name;
        this.owner = owner;
        this.status = DItemSnapshotStatus.OWNED;
        this.item = item;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DClient getOwner() {
        return owner;
    }

    public DItemSnapshotStatus getStatus() {
        return status;
    }

    public DImage getUserImage() {
        return userImage;
    }

    public DVersionedItem getData() {
        return data.getJson();
    }

    public String getEncodedString() {
        return data.getEncodedString();
    }

    public DItem getItem() {
        return item;
    }

    public List<DPriceCheck> getPriceChecks() {
        return priceChecks;
    }

    public DAuctionItem getCurrentAuction() {
        return ItemApi.findCurrentAuctionItem(this);
    }
}
