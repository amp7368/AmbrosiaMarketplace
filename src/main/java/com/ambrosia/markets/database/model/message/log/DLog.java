package com.ambrosia.markets.database.model.message.log;

import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.entity.actor.UserActor;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.discord.system.log.SendDiscordLog;
import io.ebean.annotation.DbJson;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "log")
public class DLog extends BaseEntity {

    @Id
    protected UUID id;
    @Column
    protected String logCategory;
    @Column
    protected String logType;
    @Column(columnDefinition = "text")
    protected String message;
    @DbJson
    protected Map<String, Object> json;
    @ManyToOne
    protected DClient client;
    @ManyToOne
    protected DClient actor;
    @Column
    protected Long actorDiscord;
    @Column
    protected String actorDiscordName;

    public DLog(SendDiscordLog discordLog) {
        this.logCategory = discordLog.getCategory();
        this.logType = discordLog.getLogType();
        this.message = discordLog.getMessage();
        this.json = discordLog.getJson();
        this.client = discordLog.getClient();
        UserActor actor = discordLog.getActor();
        this.actor = actor.getClient();
        this.actorDiscord = actor.getDiscordIdLong();
        this.actorDiscordName = actor.getName();
    }
}
