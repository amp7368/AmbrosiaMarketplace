package com.ambrosia.markets.database.model.interaction;

import com.ambrosia.markets.database.model.base.BaseEventEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
import io.ebean.annotation.WhenCreated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "client_click")
public class DClientClick extends BaseEventEntity {

    @Id
    protected UUID id;
    @WhenCreated
    protected Instant clickedAt;
    @Column(nullable = false)
    protected ClientClickTarget target;
    @ManyToOne(optional = false)
    protected DClient whoClicked;
}
