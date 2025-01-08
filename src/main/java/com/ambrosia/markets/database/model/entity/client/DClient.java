package com.ambrosia.markets.database.model.entity.client;

import com.ambrosia.markets.database.model.entity.client.name.ClientDiscordDetails;
import com.ambrosia.markets.database.model.entity.client.name.ClientMinecraftDetails;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameMeta;
import com.ambrosia.markets.database.model.entity.client.rank.DClientRank;
import com.ambrosia.markets.database.model.entity.staff.Rank;
import com.ambrosia.markets.database.model.profile.auction.DClientAuction;
import io.ebean.Model;
import io.ebean.annotation.History;
import io.ebean.annotation.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@History
@Entity
@Table(name = "client")
public class DClient extends Model implements ClientAccess {

    @Id
    @Identity(start = 1000)
    protected long id;

    @Column
    protected Rank rank;
    @OneToOne
    protected DClientNameMeta nameMeta;
    @OneToMany
    protected List<DClientRank> rankHistory = new ArrayList<>();

    @OneToOne
    protected DClientAuction auction = new DClientAuction();

    public DClient(DClientNameMeta nameMeta) {
        this.nameMeta = nameMeta;
        this.nameMeta.setClient(this);
    }

    public long getId() {
        return id;
    }

    @Override
    public DClient getEntity() {
        return this;
    }

    @NotNull
    public ClientMinecraftDetails getMinecraft() {
        return getNameMeta().getMinecraft();
    }

    @NotNull
    public ClientDiscordDetails getDiscord(boolean shouldUpdate) {
        return getNameMeta().getDiscord(shouldUpdate);
    }

    @Nullable
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
        return null;
//        return rank;
    }

    public DClientNameMeta getNameMeta() {
        return null;
//        return nameMeta;
    }
}
