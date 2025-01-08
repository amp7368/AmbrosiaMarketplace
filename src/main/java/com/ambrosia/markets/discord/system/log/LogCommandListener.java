package com.ambrosia.markets.discord.system.log;

import com.ambrosia.markets.Ambrosia;
import com.ambrosia.markets.database.model.message.log.CommandLogApi;
import java.time.Duration;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class LogCommandListener extends ListenerAdapter {

    public static final Duration DELAY = Duration.ofSeconds(4);

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        Ambrosia.get().schedule(new LogCommand(event), DELAY);
    }


    private record LogCommand(SlashCommandInteractionEvent event) implements Runnable {

        @Override
        public void run() {
            CommandLogApi.log(event);
        }
    }
}
