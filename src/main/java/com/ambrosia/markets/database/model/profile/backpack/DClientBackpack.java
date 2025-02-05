package com.ambrosia.markets.database.model.profile.backpack;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.client.DClient;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client_backpack")
public class DClientBackpack extends BaseEntity {

    @Id
    protected UUID id;
    @JoinColumn
    @OneToOne
    protected DClient client;
    @OneToMany
    protected List<DBackpackItem> inventory = new ArrayList<>();

    public DClientBackpack(DClient client) {
        this.client = client;
    }

    public DClientBackpack addItem(DBackpackItem item) {
        inventory.add(item);
        return this;
    }
}
