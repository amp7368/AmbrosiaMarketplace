package com.ambrosia.markets.database.model.entity.client.name;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
import io.ebean.annotation.DbJson;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "client_name_history")
public class DClientNameHistory extends BaseEntity {

    @Id
    private UUID id;
    @ManyToOne
    private DClientNameMeta clientName;
    @Column(nullable = false)
    private Instant firstUsed;
    @Column
    private Instant lastUsed;
    @Column(nullable = false)
    private NameHistoryType type;
    @Column
    private String name;
    @DbJson
    private Object nameObject;

    public DClientNameHistory(DClientNameMeta clientName, NameHistoryType type, @Nullable String name, @Nullable Object nameObject) {
        this.clientName = clientName;
        this.type = type;
        this.firstUsed = Instant.now();
        this.name = name;
        this.nameObject = nameObject;
    }

    public Instant getFirstUsed() {
        return firstUsed;
    }

    public boolean isCurrent() {
        return this.lastUsed == null;
    }

    @NotNull
    public Instant getLastUsed() {
        return Objects.requireNonNullElse(lastUsed, Instant.MAX);
    }

    public NameHistoryType getType() {
        return this.type;
    }

    public boolean isUsedAt(Instant date) {
        if (getFirstUsed().isAfter(date)) return false;
        return date.isBefore(getLastUsed());
    }

    public void retireName(Instant now) {
        this.lastUsed = now;
    }

    public DClient getClient() {
        return clientName.getClient();
    }

    public String getName() {
        return this.name;
    }
}
