import {
    ItemSlotsBase,
    ProfileBase,
    ServerProfileBase,
    UserSettingsBase,
} from '@api/io-model';
import { CreateClassFactory, CreateClassFn } from '@appleptr16/utilities';
import { Column, Entity, JoinColumn, PrimaryGeneratedColumn } from 'typeorm';

import { ItemSlots } from '../item/ref/ItemSlots.entity';
import { DiscordAccount } from './discord/DiscordAccount';
import { Credentials } from './UserCredentials';
import { WynnAccount } from './wynn/WynnAccount';
import { UserSettings } from './settings/UserSettings';

@Entity()
export class ServerProfile implements ServerProfileBase {
    static factory = new CreateClassFactory(ServerProfile, () => ({
        settings: UserSettings.create(),
        wynnAccount: WynnAccount.create(),
        discordAccount: new DiscordAccount(),
        marketSlots: ItemSlots.create(),
        pinnedSlots: ItemSlots.create(),
        playerInventory: ItemSlots.create(),
    }));
    static create = ServerProfile.factory.createFn();

    @Column(() => UserSettings)
    settings: UserSettings;

    @JoinColumn()
    @PrimaryGeneratedColumn('uuid')
    userId: string;

    @Column(() => Credentials)
    credentials: Credentials;

    @Column(() => WynnAccount)
    wynnAccount: WynnAccount;

    @Column(() => DiscordAccount)
    discordAccount: DiscordAccount;

    @Column(() => ItemSlots)
    marketSlots: ItemSlots;
    @Column(() => ItemSlots)
    pinnedSlots: ItemSlots;
    @Column(() => ItemSlots)
    playerInventory: ItemSlots;
}
