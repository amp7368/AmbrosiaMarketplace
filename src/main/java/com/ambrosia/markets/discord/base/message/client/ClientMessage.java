package com.ambrosia.markets.discord.base.message.client;

import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.entity.client.name.ClientDiscordDetails;
import com.ambrosia.markets.database.model.entity.client.name.ClientMinecraftDetails;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameMeta;
import java.util.Objects;
import java.util.Optional;
import net.dv8tion.jda.api.EmbedBuilder;
import org.jetbrains.annotations.Nullable;

public interface ClientMessage {

    static ClientMessageBuilder of(DClient client) {
        return new ClientMessageBuilder(client);
    }

    DClient getClient();

    default void clientAuthor(EmbedBuilder embed) {
        DClientNameMeta clientName = getClient().getNameMeta();

        @Nullable String display = clientName.getDisplayName();
        @Nullable String mcName = clientName.getMinecraft(ClientMinecraftDetails::getUsername);
        @Nullable String discordName = clientName.getDiscord(ClientDiscordDetails::getUsername);

        String author = getAuthorText(mcName, discordName, display);
        String url = Optional.ofNullable(mcName)
            .map(mc -> "https://wynncraft.com/stats/player/" + mc)
            .orElse(null);

        String minecraftAvatar = clientName.getMinecraft(ClientMinecraftDetails::skinUrl);
        String discordAvatar = clientName.getDiscord(ClientDiscordDetails::getAvatarUrl);

        embed.setThumbnail(Objects.requireNonNullElse(minecraftAvatar, discordAvatar));
        embed.setAuthor(author, url, discordAvatar);
    }

    private String getAuthorText(String mcName, String discordName, String display) {
        Optional<String> minecraft = Optional.ofNullable(mcName);
        Optional<String> discord = Optional.ofNullable(discordName);

        if (minecraft.isPresent() && discord.isPresent()) {
            if (display == null || display.equalsIgnoreCase(minecraft.get()))
                return minecraft.get() + "\n@" + discord.get();
            else return "%s (%s)\n@%s".formatted(minecraft.get(), display, discord.get());
        } else if (discord.isEmpty() && minecraft.isEmpty()) {
            return display;
        } else {
            String author = discord.map(s -> "@" + s)
                .orElseGet(minecraft::get);
            if (display == null || author.equalsIgnoreCase(display))
                return author;
            return author + " (%s)".formatted(display);
        }
    }
}
