package com.ambrosia.markets.discord.system.theme;

import com.ambrosia.markets.discord.DiscordBot;
import com.ambrosia.markets.discord.DiscordModule;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.entities.emoji.RichCustomEmoji;

public enum AppEmoji {
    ANY_DATE(1208248441805738044L),
    CHECK_SUCCESS(1208248444632965211L),
    CHECK_ERROR(1208252574726357092L),
    CLIENT_ACCOUNT(1208251350593314817L),
    CLIENT_MINECRAFT(1208248447702933574L),
    STATUS_ACTIVE(1208248428992270376L),
    STATUS_COMPLETE(1208248429994573854L),
    STATUS_PENDING(1208248433023123517L),
    STATUS_OFFLINE(1208248432028950598L),
    STATUS_ERROR(1208248431076708433L),
    TOS("\uD83D\uDCDC");

    private final long emojiId;
    private RichCustomEmoji emoji;
    private String emojiStr;

    AppEmoji(long emojiId) {
        this.emojiId = emojiId;
    }

    AppEmoji(String emojiStr) {
        this.emojiId = 0;
        this.emojiStr = emojiStr;
    }

    public RichCustomEmoji getEmoji() {
        if (this.emoji != null) return this.emoji;
        return this.emoji = DiscordBot.dcf.jda().getEmojiById(emojiId);
    }

    @Override
    public String toString() {
        if (this.emojiStr != null) return emojiStr;
        RichCustomEmoji em = getEmoji();
        if (em == null) {
            DiscordModule.get().logger().error("{} emoji not found!!", name());
            return "Emoji not found";
        }
        this.emojiStr = em.getFormatted();
        return emojiStr;
    }

    public String spaced() {
        return this + " ";
    }

    public String spaced(Object msg) {
        return spaced() + msg;
    }

    public Emoji getDiscordEmoji() {
        return Emoji.fromUnicode(emojiStr);
    }
}
