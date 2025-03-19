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
import com.ambrosia.markets.database.system.exception.AlreadySoldException;
import com.ambrosia.markets.database.wynncraft.item.base.DVersionedItem;
import com.ambrosia.markets.util.theme.format.AppFormattable;
import com.ambrosia.markets.util.theme.format.entity.EntityFormatter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "item_snapshot")
public class DItemSnapshot extends BaseEntity implements AppFormattable<EntityFormatter<DItemSnapshot, ?>> {

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
    @JoinColumn
    @OneToOne
    protected DTransferAction transfer;
    @ManyToOne
    protected DTransferAction selling; // todo delete?
    @ManyToOne
    protected DTransferAction bought; // todo delete?
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

    protected transient EntityFormatter<DItemSnapshot, ?> formatter = EntityFormatter.prop(this);

    public DItemSnapshot(String name, DClient owner, DItem item, DItemData data) {
        this.name = name;
        this.owner = owner;
        this.status = DItemSnapshotStatus.OWNED;
        this.item = item;
        this.data = data;
    }

    public DItemSnapshot(DItemSnapshot transferredFrom) {
        this.name = transferredFrom.getUserDisplayName();
        this.owner = transferredFrom.getSoldTo();
        this.status = DItemSnapshotStatus.OWNED;
        this.item = transferredFrom.getItem();
        this.data = transferredFrom.data;
    }

    @NotNull
    @Override
    public EntityFormatter<DItemSnapshot, ?> formatter() {
        return EntityFormatter.create(this, item -> item.getData().name());
    }

    public boolean isCurrentlyOwned() {
        return status == DItemSnapshotStatus.OWNED;
    }

    public void verifyCurrentlyOwned() throws AlreadySoldException {
        if (isCurrentlyOwned()) return;
        throw new AlreadySoldException(this);
    }

    public DItemSnapshot sell(DTransferAction transfer, DClient soldTo) throws AlreadySoldException {
        verifyCurrentlyOwned();
        this.soldTo = soldTo;
        this.transfer = transfer;
        this.status = DItemSnapshotStatus.TRANSFERRED;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getUserDisplayName() {
        return name;
    }

    public DClient getOwner() {
        return owner;
    }

    public DClient getSoldTo() {
        return soldTo;
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

    public boolean hasOldAuction() {
        return ItemApi.hasOldAuction(this);
    }

    public boolean isEqual(DItemSnapshot other) {
        return this.getId().equals(other.getId());
    }
}
