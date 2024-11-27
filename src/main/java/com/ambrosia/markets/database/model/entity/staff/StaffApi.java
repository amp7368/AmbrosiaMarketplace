package com.ambrosia.markets.database.model.entity.staff;

import com.ambrosia.markets.database.model.entity.client.ClientApi.ClientQueryApi;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.entity.staff.query.QDStaffConductor;
import org.jetbrains.annotations.Nullable;

public interface StaffApi {

    static DStaffConductor findByDiscord(long discordId) {
        return new QDStaffConductor().where()
            .client.nameMeta.discord.id
            .eq(discordId)
            .findOne();
    }

    static DStaffConductor create(DClient client) {
        DStaffConductor conductor = new DStaffConductor(client);
        conductor.save();
        return conductor;
    }

    @Nullable
    static DStaffConductor findByDiscordOrConvert(long staffDiscord, Rank rank) {
        DStaffConductor conductor = findByDiscord(staffDiscord);
        if (conductor != null) return conductor;

        DClient client = ClientQueryApi.findByDiscord(staffDiscord);
        if (client == null) return null;
        return create(client);
    }
}
