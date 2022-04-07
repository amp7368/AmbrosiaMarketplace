import { WynnAccountBase } from '@api/io-model';
import { CreateClassFactory } from '@appleptr16/utilities';
import { Column } from 'typeorm';

export class WynnAccount implements WynnAccountBase {
    static factory = new CreateClassFactory(WynnAccount);
    static create = WynnAccount.factory.createFn();

    @Column({ nullable: true })
    playerUUID: string;
}
