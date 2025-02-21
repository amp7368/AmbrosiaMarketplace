package com.ambrosia.markets.database.model.message.mail;

import com.ambrosia.markets.database.model.entity.client.DClient;
import io.ebean.annotation.DbJson;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.Instant;
import java.util.UUID;

public class DMail {

    @Id
    public UUID id;

    @ManyToOne(optional = false)
    public DClient to;
    @ManyToOne
    public DClient sender;
    @WhenCreated
    public Instant sentAt;
    @WhenModified
    public Instant modifiedAt;
    @Column
    public MailReadState readState;

    @ManyToOne
    public DMail replied;

    @Column
    public String icon;
    @Column(nullable = false)
    public String title;
    @Column(nullable = false)
    public String message;
    @Column(nullable = false)
    public String from;

    @Column(nullable = false)
    public String version;
    @Column(nullable = false)
    public String mailType;

    @DbJson
    public Object data;

    public DMail() {
    }
}
