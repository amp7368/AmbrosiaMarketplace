package com.ambrosia.markets.database.model.entity.client.rank;

import com.ambrosia.markets.database.model.base.BareBaseEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.entity.staff.Rank;
import io.ebean.annotation.WhenCreated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "client_rank")
public class DClientRank extends BareBaseEntity {

    @Id
    protected UUID id;
    @ManyToOne
    protected DClient client;
    @Column(nullable = false)
    protected Rank rank;
    @WhenCreated
    protected Instant addedAt;
    @Column
    protected Instant removedAt;
}
