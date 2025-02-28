package com.ambrosia.markets.database.model.entity.client;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.client.name.ClientDiscordDetails;
import com.ambrosia.markets.database.model.entity.client.name.ClientMinecraftDetails;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameMeta;
import com.ambrosia.markets.database.model.entity.client.rank.DClientRank;
import com.ambrosia.markets.database.model.entity.staff.Rank;
import com.ambrosia.markets.database.model.profile.auction.DClientAuction;
import com.ambrosia.markets.database.model.profile.backpack.DClientBackpack;
import io.ebean.annotation.History;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

@History
@Entity
@Table(name = "client")
public class DClient extends BaseEntity {

    @Id
    protected UUID id;

    @Column
    protected Rank rank;
    @OneToOne(mappedBy = "client")
    protected DClientNameMeta nameMeta;
    @OneToMany
    protected List<DClientRank> rankHistory = new ArrayList<>();

    @OneToOne(mappedBy = "client")
    protected DClientAuction auction;
    @OneToOne(mappedBy = "client")
    protected DClientBackpack backpack;

    public DClient() {
    }

    /**
     * ebean says not to implement Entity#equals, so I won't
     *
     * @param a the first client
     * @param b the second client
     * @return true if the clients refer to the same individual (same id)
     */
    public static boolean isEqual(@NotNull DClient a, @NotNull DClient b) {
        return a.getId().equals(b.getId());
    }

    public UUID getId() {
        return id;
    }

    @NotNull
    public ClientMinecraftDetails getMinecraft() {
        return getNameMeta().getMinecraft();
    }

    @NotNull
    public ClientDiscordDetails getDiscord(boolean shouldUpdate) {
        return getNameMeta().getDiscord(shouldUpdate);
    }

    @NotNull
    public ClientDiscordDetails getDiscord() {
        return getDiscord(true);
    }

    public String getEffectiveName() {
        return getNameMeta().getEffectiveName();
    }

    @Override
    public String toString() {
        return getNameMeta().getEffectiveName();
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public DClientNameMeta getNameMeta() {
        return nameMeta;
    }

    public DClientBackpack getBackpack() {
        if (backpack == null) {
            this.backpack = new DClientBackpack(this);
            this.backpack.save();
        }
        return backpack;
    }

    public DClientAuction getAuction() {
        if (auction == null) {
            this.auction = new DClientAuction(this);
            this.auction.save();
        }
        return auction;
    }

    public void init(DClientNameMeta nameMeta, DClientBackpack backpack, DClientAuction auction) {
        this.nameMeta = nameMeta;
        this.backpack = backpack;
        this.auction = auction;
    }
}
