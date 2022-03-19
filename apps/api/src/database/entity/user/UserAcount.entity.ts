import {
    ItemSlotsBase,
    ProfileBase,
    ServerProfileBase,
    UserSettingsBase,
} from '@api/io-model';
import { CreateClassFactory } from '@appleptr16/utilities';
import { Column, Entity, JoinColumn, PrimaryGeneratedColumn } from 'typeorm';

import { ItemSlots } from '../item/ref/ItemSlots.entity';
import { DiscordAccount } from './discord/DiscordAccount';
import { Credentials } from './UserCredentials';
import { WynnAccount } from './wynn/WynnAccount';
import { CreateClassFn } from '../../../../../../libs/misc-utilities/src/lib/factory/CreateClassFactory';
import { UserSettings } from './settings/UserSettings';

@Entity()
export class ServerProfile implements ServerProfileBase {
    static factory = new CreateClassFactory(ServerProfile);
    static create: CreateClassFn<ServerProfile> = ServerProfile.factory.create;

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
    marketSlots: ItemSlotsBase;
    @Column(() => ItemSlots)
    pinnedSlots: ItemSlotsBase;
    @Column(() => ItemSlots)
    playerInventory: ItemSlotsBase;
}
