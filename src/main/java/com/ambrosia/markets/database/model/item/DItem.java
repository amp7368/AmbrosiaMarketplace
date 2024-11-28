package com.ambrosia.markets.database.model.item;

import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "item")
public class DItem {

    @Id
    protected UUID id;
    @OneToMany
    protected List<DItemSnapshot> snapshots = new ArrayList<>();
}
