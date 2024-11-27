package com.ambrosia.markets.database.model.base;

import io.ebean.Model;
import io.ebean.annotation.WhenModified;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public class BaseEventEntity extends Model {

    @WhenModified
    protected Instant modifiedAt;

    public Instant getModifiedAt() {
        return modifiedAt;
    }
}
