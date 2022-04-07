import { ContactSettingsBase } from '@api/io-model';
import { CreateClassFactory, CreateClassFn } from '@appleptr16/utilities';
import { Column } from 'typeorm';

export class ContactSettings implements ContactSettingsBase {
    static factory = new CreateClassFactory(ContactSettings, () => ({
        showDiscord: false,
    }));
    static create = ContactSettings.factory.createFn();

    @Column('boolean')
    showDiscord: boolean;
}
