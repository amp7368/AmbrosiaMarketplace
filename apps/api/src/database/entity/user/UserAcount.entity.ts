import { RequestSignup } from 'libs/api-iomodel/src/api';
import { UserAccountBase } from '@api/io-model';
import { Column, Entity, JoinColumn, PrimaryColumn } from 'typeorm';
import { v4 } from 'uuid';
import { AmbrosiaAccount } from './ambrosia/AmbrosiaAccount';
import { DiscordAccount } from './discord/DiscordAccount';
import { UserCredentials } from './UserCredentials';
import { WynnAccount } from './wynn/WynnAccount';

@Entity()
export class UserAccount implements UserAccountBase {
    @JoinColumn()
    @PrimaryColumn('uuid')
    userId: string;

    @Column(() => UserCredentials)
    credentials: UserCredentials;

    @Column(() => WynnAccount)
    wynnAccount: WynnAccount;

    @Column(() => DiscordAccount)
    discordAccount: DiscordAccount;

    @Column(() => AmbrosiaAccount)
    ambrosiaAccount: AmbrosiaAccount;

    assignProps(props: RequestSignup) {
        this.userId = v4();
        this.credentials = new UserCredentials();
        this.credentials.assignProps(props);
        this.wynnAccount = new WynnAccount();
        this.discordAccount = new DiscordAccount();
        this.ambrosiaAccount = new AmbrosiaAccount();
    }
}
