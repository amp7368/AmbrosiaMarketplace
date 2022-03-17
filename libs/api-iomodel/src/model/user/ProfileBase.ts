import { DiscordAccountBase } from './DiscordAccountBase';
import { CredentialsBase } from './CredentialsBase';
import { WynnAccountBase } from './WynnAccountBase';
import { UserSettingsBase } from './settings/UserSettingsBase';
import { ItemSlotsBase } from '../item/ItemSlotsBase';

export interface ServerProfileBase extends ClientProfileBase {
    credentials: CredentialsBase;
}

export interface ClientProfileBase extends ProfileBase {
    settings: UserSettingsBase;
    wynnAccount: WynnAccountBase;
    discordAccount: DiscordAccountBase;
}

export interface ProfileBase {
    userId: string;
    marketSlots: ItemSlotsBase;
    pinnedSlots: ItemSlotsBase;
    playerInventory: ItemSlotsBase;
}
