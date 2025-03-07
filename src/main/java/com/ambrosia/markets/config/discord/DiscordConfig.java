package com.ambrosia.markets.config.discord;

import com.ambrosia.markets.discord.DiscordBot;
import com.ambrosia.markets.discord.DiscordModule;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class DiscordConfig {

    private static DiscordConfig instance;
    protected String token = "token";
    protected long mainServerId = 0;
    protected long logChannel = 0;
    protected long requestChannel = 0;
    protected String applicationToken = "appToken";
    protected boolean shouldEnable = true;

    public DiscordConfig() {
        instance = this;
    }

    public static DiscordConfig get() {
        return instance;
    }

    public static Guild getMainServer() {
        return DiscordBot.jda().getGuildById(DiscordConfig.get().mainServerId);
    }

    public String getToken() {
        return token;
    }

    public boolean isConfigured() {
        return !this.token.equals("token");
    }

    public void generateWarnings() {
        if (logChannel == 0) DiscordModule.get().logger().error("Log dest is not set");
        if (requestChannel == 0) DiscordModule.get().logger().error("Request dest is not set");
    }

    public long getLogChannelId() {
        return logChannel;
    }

    public TextChannel getLogChannel() {
        return DiscordBot.jda().getTextChannelById(logChannel);
    }

    public TextChannel getRequestChannel() {
        return DiscordBot.jda().getTextChannelById(requestChannel);
    }

    public void load() {
        getLogChannel();
        getRequestChannel();
        getMainServer();
    }

    public boolean isInServer(Guild guild) {
        if (guild == null) return false;
        return guild.getIdLong() == mainServerId;
    }

    public String getApplicationToken() {
        return this.applicationToken;
    }

    public boolean shouldEnable() {
        return this.shouldEnable;
    }
}
