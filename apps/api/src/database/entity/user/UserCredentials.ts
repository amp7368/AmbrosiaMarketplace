import { CredentialsBase } from '@api/io-model';
import { CreateClassFactory, CreateClassFn } from '@appleptr16/utilities';
import { Column } from 'typeorm';

export class Credentials implements CredentialsBase {
    static factory = new CreateClassFactory(Credentials);
    static create: CreateClassFn<Credentials> = Credentials.factory.createFn();

    @Column({ nullable: true })
    email: string;

    @Column()
    username: string;

    @Column()
    password: string;
}
