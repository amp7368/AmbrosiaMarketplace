package com.ambrosia.markets.discord.system.log.modifier;

import apple.utilities.util.Pretty;
import com.ambrosia.markets.discord.system.log.SendDiscordLog;
import com.ambrosia.markets.util.theme.AmbrosiaAssets.AmbrosiaEmoji;
import io.ebean.DB;
import java.util.Comparator;

@FunctionalInterface
public interface DiscordLogModifier {

    Comparator<DiscordLogModifier> COMPARATOR = Comparator.comparing(DiscordLogModifier::getPriority);

    static DiscordLogModifier addEntity(String entityType, String entityId) {
        String msg = "%s %s %s".formatted(Pretty.spaceEnumWords(entityType), AmbrosiaEmoji.KEY_ID, entityId);
        return log -> log.prependMsg(msg);
    }

    static DiscordLogModifier addEntity(String entityType, Object entity) {
        return addEntity(entityType, DB.beanId(entity).toString());
    }

    static DiscordLogModifier setColor(int color) {
        return log -> log.setColor(color);
    }

    default int getPriority() {
        return 0;
    }

    default DiscordLogModifier withPriority(int priority) {
        return new DiscordLogModifierImpl(priority, this);
    }

    void modify(SendDiscordLog log);

}
