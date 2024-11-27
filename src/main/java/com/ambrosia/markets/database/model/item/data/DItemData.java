package com.ambrosia.markets.database.model.item.data;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.wynncraft.item.base.VersionedItem;
import io.ebean.annotation.DbJsonB;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "item_data")
public class DItemData extends BaseEntity {

    @Id
    protected UUID id;
    @DbJsonB
    protected VersionedItem json;

}
