package com.ambrosia.markets.discord.system.log.modifier;


import com.ambrosia.markets.discord.system.log.DiscordLogService;

public record DiscordLogModifierImpl(int priority, DiscordLogModifier base) implements DiscordLogModifier {

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public void modify(DiscordLogService log) {
        base.modify(log);
    }
}
