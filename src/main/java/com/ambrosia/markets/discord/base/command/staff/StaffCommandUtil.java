package com.ambrosia.markets.discord.base.command.staff;

import com.ambrosia.markets.database.model.entity.staff.DStaffConductor;
import com.ambrosia.markets.database.model.entity.staff.Rank;
import com.ambrosia.markets.database.model.entity.staff.StaffApi;
import com.ambrosia.markets.discord.system.theme.AppMessages.ErrorMessages;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.Nullable;

public interface StaffCommandUtil {

    @Nullable
    static DStaffConductor getOrMakeStaff(SlashCommandInteractionEvent event) {
        long discordId = event.getUser().getIdLong();
        DStaffConductor staff = StaffApi.findByDiscordOrConvert(discordId, Rank.EMPLOYEE);
        if (staff == null)
            ErrorMessages.error("Register your account first!").replyError(event);
        return staff;
    }
}
