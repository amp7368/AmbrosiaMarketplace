package com.ambrosia.markets.discord;

import apple.lib.modules.AppleModule;
import com.ambrosia.markets.Ambrosia;
import com.ambrosia.markets.config.discord.DiscordConfig;
import com.ambrosia.markets.config.discord.DiscordPermissions;
import com.ambrosia.markets.discord.base.command.BaseCommand;
import com.ambrosia.markets.discord.base.command.BaseSubCommand;
import com.ambrosia.markets.discord.misc.autocomplete.AutoCompleteListener;
import com.ambrosia.markets.discord.request.RequestCommand;
import com.ambrosia.markets.discord.system.help.HelpCommandListManager;
import com.ambrosia.markets.discord.system.log.SendDiscordLog;
import discord.util.dcf.DCF;
import discord.util.dcf.DCFCommandManager;
import discord.util.dcf.slash.DCFAbstractCommand;
import discord.util.dcf.slash.DCFSlashCommand;
import discord.util.dcf.slash.DCFSlashSubCommand;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class DiscordModule extends AppleModule {

    public static final int MAX_CHOICES = 25;
    public static final ZoneId TIME_ZONE = ZoneId.of("America/Los_Angeles");
    public static final DateTimeFormatter SIMPLE_DATE_FORMATTER = new DateTimeFormatterBuilder()
        .appendPattern("MM/dd/yy")
        .parseDefaulting(ChronoField.SECOND_OF_DAY, 0)
        .parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
        .toFormatter()
        .withZone(DiscordModule.TIME_ZONE);

    private static DiscordModule instance;

    public DiscordModule() {
        instance = this;
    }

    public static DiscordModule get() {
        return instance;
    }

    private void updateCommandsCallback(List<Command> commands) {
        for (Command command : commands) {
            DCFAbstractCommand<?> abstractCommand = DiscordBot.dcf.commands().getCommand(command.getFullCommandName());
            if (!(abstractCommand instanceof DCFSlashCommand baseCommand)) continue;

            boolean isStaffCommand = isStaffCommand(baseCommand);
            boolean isManagerCommand = isMangerCommand(baseCommand);
            for (DCFSlashSubCommand dcfSub : baseCommand.getSubCommands()) {
                if (!(dcfSub instanceof BaseSubCommand subCommand)) continue;

                if (isStaffCommand)
                    subCommand.setOnlyEmployee();
                if (isManagerCommand)
                    subCommand.setOnlyManager();
            }
            HelpCommandListManager.addCommand(baseCommand, isStaffCommand, isManagerCommand);
        }
        HelpCommandListManager.finishSetup();
    }

    private boolean isStaffCommand(DCFAbstractCommand<?> abstractCommand) {
        if (abstractCommand instanceof BaseCommand dcfCommand) {
            return dcfCommand.isOnlyEmployee();
        } else if (abstractCommand instanceof BaseSubCommand dcfCommand) {
            return dcfCommand.isOnlyEmployee();
        }
        return false;
    }

    private boolean isMangerCommand(DCFAbstractCommand<?> abstractCommand) {
        if (abstractCommand instanceof BaseCommand dcfCommand) {
            return dcfCommand.isOnlyManager();
        } else if (abstractCommand instanceof BaseSubCommand dcfCommand) {
            return dcfCommand.isOnlyManager();
        }
        return false;
    }

    @Override
    public String getName() {
        return "Discord";
    }

    @Override
    public void onLoad() {
        DiscordConfig.get().generateWarnings();
        DiscordPermissions.get().generateWarnings();
        if (!DiscordConfig.get().isConfigured()) {
            this.logger().fatal("Please configure {}", Ambrosia.get().getFile("AmbrosiaConfig.json"));
            System.exit(1);
        }
    }

    @Override
    public void onEnablePost() {
        if (!DiscordConfig.get().shouldEnable()) return;
        DiscordConfig.get().load();
        SendDiscordLog.load();
        DiscordBot.dcf.commands().updateCommands(
            action -> action,
            this::updateCommandsCallback
        );
    }

    @Override
    public void onEnable() {
        if (!DiscordConfig.get().shouldEnable()) return;
        JDABuilder builder = JDABuilder.createDefault(DiscordConfig.get().getToken(), GatewayIntent.GUILD_EMOJIS_AND_STICKERS)
            .disableCache(CacheFlag.VOICE_STATE, CacheFlag.STICKER, CacheFlag.SCHEDULED_EVENTS);
        JDA jda = builder.build();
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        jda.getPresence().setPresence(Activity.customStatus("Ambrosia a path"), false);

        DCF dcf = new DCF(jda);
        DiscordBot.ready(dcf);

        jda.addEventListener(new AutoCompleteListener());
        DCFCommandManager commands = dcf.commands();

        commands.addCommand(new RequestCommand());
    }
}
