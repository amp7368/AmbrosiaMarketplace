package com.ambrosia.markets.discord.request.image;

import com.ambrosia.markets.config.AmbrosiaConfig;
import com.ambrosia.markets.database.model.base.image.DImage;
import com.ambrosia.markets.discord.base.command.BaseSubCommand;
import com.ambrosia.markets.discord.base.command.option.CommandOption;
import com.ambrosia.markets.discord.base.command.option.CommandOptionList;
import com.ambrosia.markets.discord.system.theme.AmbrosiaColor;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message.Attachment;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class AddImageCommand extends BaseSubCommand {

    private static DImage createImage(String name, Attachment attachment) {
        File file;
        try {
            file = File.createTempFile("ambrosia-marketplace", "img");
            attachment.getProxy().downloadToFile(file).get();
        } catch (IOException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        DImage image = new DImage(file, name, attachment.getFileExtension(), attachment.getContentType());
        image.save();
        file.delete();
        return image;
    }

    private static void replySuccess(InteractionHook defer, DImage image) {
        String domain = AmbrosiaConfig.getApi().getDomain();
        String imageUrl = "https://%s/v1/assets/%s".formatted(domain, image.getId());
        MessageEmbed embed = new EmbedBuilder()
            .setImage(imageUrl)
            .setAuthor(image.getName(), imageUrl)
            .setColor(AmbrosiaColor.GREEN)
            .setDescription("Successfully added an image: %s\n<%s>".formatted(image.getId(), imageUrl))
            .build();
        defer.editOriginalEmbeds(embed).queue();
    }

    @Override
    protected void onCheckedCommand(SlashCommandInteractionEvent event) {
        String name = CommandOption.DISPLAY_NAME.getRequired(event);
        if (name == null) return;
        Attachment attachment = CommandOption.IMAGE.getRequired(event);
        if (attachment == null) return;
        event.deferReply().queue(
            defer -> {
                DImage image = createImage(name, attachment);
                replySuccess(defer, image);
            }
        );

    }

    @Override
    public SubcommandData getData() {
        return CommandOptionList.of(
            List.of(CommandOption.DISPLAY_NAME, CommandOption.IMAGE)
        ).addToCommand(new SubcommandData("addimage", "Add a new image to the database."));
    }
}
