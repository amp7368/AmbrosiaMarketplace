package com.ambrosia.markets.database.model.entity.staff;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
import io.ebean.DB;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "staff")
public class DStaffConductor extends BaseEntity {

    @Id
    protected long id;
    @Column(nullable = false)
    protected String username;
    @Column(nullable = false)
    protected Rank rank;

    @Nullable
    @JoinColumn
    @OneToOne
    protected DClient client;

    public DStaffConductor(DClient client) {
        this.id = client.getId();
        this.client = client;
        this.username = client.getEffectiveName();
        this.rank = client.getRank();
    }

    private DStaffConductor(long id, String username) {
        this.id = id;
        this.username = username;
        this.rank = Rank.SYSTEM;
    }


    public static synchronized DStaffConductor insertSystemConductor(String systemUsername, long systemId) {
        DStaffConductor conductor = DB.find(DStaffConductor.class, systemId);
        if (conductor != null) return conductor;

        conductor = new DStaffConductor(systemId, systemUsername);
        conductor.save();
        return conductor;
    }

    public String getName() {
        if (this.client == null) return this.username;
        String username = this.client.getEffectiveName();
        if (!this.username.equals(username)) {
            this.username = username;
            this.save();
        }
        return username;
    }

    @Nullable
    public DClient getClient() {
        return this.client;
    }
}
