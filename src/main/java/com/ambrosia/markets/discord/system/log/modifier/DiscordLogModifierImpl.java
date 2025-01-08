package com.ambrosia.markets.discord.system.log.modifier;

import com.ambrosia.markets.discord.system.log.SendDiscordLog;

public record DiscordLogModifierImpl(int priority, DiscordLogModifier base) implements DiscordLogModifier {

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public void modify(SendDiscordLog log) {
        base.modify(log);
    }
}
