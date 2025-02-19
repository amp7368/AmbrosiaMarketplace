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
    public final String discordAvatarUrl;
    @Nullable
    public final String minecraftName;
    @Nullable
    public final UUID minecraftUUID;
    @Nullable
    public final String minecraftProfileUrl;

    public final String profileImage;

    public final Rank rank;

    public final long auctionedItemsCount;
    public final long boughtItemsCount;

    public PublicUserResponse(DClient client) {
        this.name = client.getEffectiveName();
        this.discordName = client.getDiscord().getUsername();
        this.discordAvatarUrl = client.getDiscord().getAvatarUrl();
        this.minecraftName = client.getMinecraft().getUsername();
        this.minecraftUUID = client.getMinecraft().getUUID();
        if (this.minecraftUUID != null)
            this.minecraftProfileUrl = "https://mc-heads.net/body/%s/200".formatted(minecraftUUID);
        else this.minecraftProfileUrl = "https://mc-heads.net/body//200";
        this.profileImage = null;
        this.rank = client.getRank();
        this.auctionedItemsCount = -1;
        this.boughtItemsCount = -1;
    }
}
