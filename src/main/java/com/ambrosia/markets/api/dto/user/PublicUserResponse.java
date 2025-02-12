package com.ambrosia.markets.api.dto.user;

import com.ambrosia.markets.api.base.BaseResponse;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.entity.staff.Rank;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class PublicUserResponse extends BaseResponse {

    public final String name;
    @Nullable
    public final String discordName;
    @Nullable
    public final String minecraftName;
    @Nullable
    public final UUID minecraftUUID;

    public final String profileImage;

    public final Rank rank;

    public final long auctionedItemsCount;
    public final long boughtItemsCount;

    public PublicUserResponse(DClient client) {
        this.name = client.getEffectiveName();
        this.discordName = client.getDiscord().getUsername();
        this.minecraftName = client.getMinecraft().getUsername();
        this.minecraftUUID = client.getMinecraft().getUUID();
        this.profileImage = null;
        this.rank = client.getRank();
        this.auctionedItemsCount = -1;
        this.boughtItemsCount = -1;
    }
}
