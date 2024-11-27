package com.ambrosia.markets.database.model.item;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "item")
public class DItem {

    @Id
    protected UUID id;
    // todo
}
