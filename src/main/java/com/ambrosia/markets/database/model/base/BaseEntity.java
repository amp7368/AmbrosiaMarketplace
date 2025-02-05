package com.ambrosia.markets.database.model.base;

import io.ebean.Model;
import io.ebean.annotation.DbDefault;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import io.ebean.config.dbplatform.DbDefaultValue;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity extends Model {

    @DbDefault(DbDefaultValue.NOW)
    @WhenCreated
    protected Instant createdAt;
    @DbDefault(DbDefaultValue.NOW)
    @WhenModified
    protected Instant modifiedAt;

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }
}
