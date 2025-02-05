package com.ambrosia.markets.database.model.item.data;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.wynncraft.item.base.DVersionedItem;
import io.ebean.annotation.DbJsonB;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "item_data")
public class DItemData extends BaseEntity {

    @Id
    protected UUID id;
    @Column(nullable = false)
    protected String encodedString;
    @DbJsonB
    protected DVersionedItem json;

    public DItemData(String encodedString, DVersionedItem json) {
        this.encodedString = encodedString;
        this.json = json;
    }

    public UUID getId() {
        return id;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public DVersionedItem getJson() {
        return json.deepCopy();
    }
}
