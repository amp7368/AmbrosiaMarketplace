package com.ambrosia.markets.database.model.message.log;

import com.ambrosia.markets.database.model.entity.client.DClient;
import io.ebean.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "command_log")
public class DCommandLog extends Model {

    @Id
    protected UUID id;
    @Column(nullable = false)
    protected Timestamp createdAt;
    @Column(nullable = false, columnDefinition = "text")
    protected String commandMessage;

    @Column(nullable = false)
    protected Long senderDiscordId;
    @Column(nullable = false)
    protected String senderDiscordName;
    @Column(nullable = false)
    protected String senderEffectiveName;
    @ManyToOne // optional
    protected DClient senderClient;

    @Column(nullable = false)
    protected long channelId;
    @Column(nullable = false)
    protected String channelName;
    @Column(nullable = false)
    protected String channelType;
    @Column
    protected Long serverId;
    @Column
    protected String serverName;

    public DCommandLog(String commandMessage,
        User senderDiscord, @Nullable DClient senderClient,
        @Nullable Guild server, MessageChannelUnion channel) {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.commandMessage = commandMessage;

        this.senderDiscordId = senderDiscord.getIdLong();
        this.senderDiscordName = senderDiscord.getName();
        this.senderEffectiveName = senderDiscord.getEffectiveName();
        this.senderClient = senderClient;

        this.channelId = channel.getIdLong();
        this.channelName = channel.getName();
        this.channelType = channel.getType().name();

        if (server != null) {
            this.serverId = server.getIdLong();
            this.serverName = server.getName();
        }
    }
}
