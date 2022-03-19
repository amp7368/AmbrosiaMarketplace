import {
    ContactSettingsBase,
    CredentialsBase,
    UserSettingsBase,
} from '@api/io-model';
import { CreateClassFactory, CreateClassFn } from '@appleptr16/utilities';
import { Column } from 'typeorm';

export class ContactSettings implements ContactSettingsBase {
    static factory = new CreateClassFactory(ContactSettings);
    static create: CreateClassFn<ContactSettings> =
        ContactSettings.factory.create;

    @Column('boolean')
    showDiscord: boolean;
}
