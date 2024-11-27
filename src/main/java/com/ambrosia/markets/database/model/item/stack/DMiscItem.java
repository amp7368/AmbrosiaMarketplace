package com.ambrosia.markets.database.model.item.stack;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.base.image.DImage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "misc_item")
public class DMiscItem extends BaseEntity {

    @Id
    protected UUID id;
    @Column(nullable = false)
    protected String name;
    @Column(columnDefinition = "text")
    protected String description;
    @ManyToOne
    protected DImage image;
}
