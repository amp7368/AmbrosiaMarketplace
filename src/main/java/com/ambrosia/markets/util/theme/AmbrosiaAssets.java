package com.ambrosia.markets.util.theme;

import com.ambrosia.markets.discord.DiscordBot;
import com.ambrosia.markets.discord.DiscordModule;
import java.text.MessageFormat;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.entities.emoji.RichCustomEmoji;

public class AmbrosiaAssets {

    public static final String EMERALD = gif("Emerald.v1");

    private static String gif(String file) {
        return img(file + ".gif");
    }

    private static String png(String file) {
        return img(file + ".png");
    }

    private static String img(String file) {
        return "https://static.voltskiya.com/ambrosia/loans/" + file;
    }

    public enum AmbrosiaEmoji {
        KEY_ID_CHANGES(":apple:"),
        KEY_ID(":apple:"),
        ANY_DATE(":apple:"),
        CLIENT_ACCOUNT(":apple:");


        private final long emojiId;
        private RichCustomEmoji emoji;
        private String emojiStr;

        AmbrosiaEmoji(long emojiId) {
            this.emojiId = emojiId;
        }

        AmbrosiaEmoji(String emojiStr) {
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
            return MessageFormat.format("{0} ", this);
        }

        public String spaced(Object msg) {
            return spaced() + msg;
        }

        public Emoji getDiscordEmoji() {
            return Emoji.fromUnicode(emojiStr);
        }
    }
}
