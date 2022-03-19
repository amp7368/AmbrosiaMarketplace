import { ContactSettingsBase, UserSettingsBase } from '@api/io-model';
import { CreateClassFactory, CreateClassFn } from '@appleptr16/utilities';
import { Column } from 'typeorm';

import { ContactSettings } from './ContactSettings';

export class UserSettings implements UserSettingsBase {
    static factory = new CreateClassFactory(UserSettings);
    static create: CreateClassFn<UserSettings> = UserSettings.factory.create;

    @Column(() => ContactSettings)
    contactSettings: ContactSettingsBase;
}
