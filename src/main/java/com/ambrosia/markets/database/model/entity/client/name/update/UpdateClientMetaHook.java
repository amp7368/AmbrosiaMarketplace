package com.ambrosia.markets.database.model.entity.client.name.update;

import com.ambrosia.markets.Ambrosia;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameMeta;
import com.ambrosia.markets.database.model.entity.client.name.NameHistoryType;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public record UpdateClientMetaHook(UUID id, Instant startedUpdateAt) {

    public static final Duration TIME_TO_OLD = Duration.ofMinutes(5);
    private static final Map<UUID, UpdateClientMetaHook> ACTIVELY_UPDATING = new HashMap<>();

    public static void hookUpdate(DClientNameMeta client) {
        Ambrosia.get().schedule(() -> hookUpdateTask(client), 50);
    }

    private static void hookUpdateTask(DClientNameMeta nameMeta) {
        UpdateClientMetaHook task;
        synchronized (ACTIVELY_UPDATING) {
            UpdateClientMetaHook oldTask = ACTIVELY_UPDATING.get(nameMeta.getId());
            if (oldTask != null && !oldTask.isTaskOld())
                return;
            task = new UpdateClientMetaHook(nameMeta.getId(), Instant.now());
            ACTIVELY_UPDATING.put(nameMeta.getId(), task);
        }
        try {
            nameMeta.refresh();
            Future<Void> mcTask = UpdateClientMinecraftHook.minecraftUpdate(nameMeta);
            Future<Void> dcTask = UpdateClientDiscordHook.discordUpdate(nameMeta);
            nameMeta.getNameNow(NameHistoryType.DISCORD_USER);
            nameMeta.getNameNow(NameHistoryType.MINECRAFT);
            nameMeta.getNameNow(NameHistoryType.DISPLAY_NAME);
            if (mcTask != null) mcTask.get();
            if (dcTask != null) dcTask.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            task.scheduleRemove();
        }
    }

    private void scheduleRemove() {
        Instant expireAt = startedUpdateAt.plus(TIME_TO_OLD);
        Duration timeToExpire = Duration.between(Instant.now(), expireAt);

        Ambrosia.get().schedule(this::remove, timeToExpire);
    }

    private void remove() {
        synchronized (ACTIVELY_UPDATING) {
            ACTIVELY_UPDATING.remove(id());
        }
    }

    private boolean isTaskOld() {
        Duration timeTaken = Duration.between(startedUpdateAt, Instant.now());
        return timeTaken.compareTo(TIME_TO_OLD) > 0;
    }

}
