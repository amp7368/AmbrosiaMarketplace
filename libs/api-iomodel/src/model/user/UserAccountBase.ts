import { UserCredentialsBase } from './UserCredentialsBase';
import { AmbrosiaAccountBase } from './ambrosia/AmbrosiaAccountBase';
import { DiscordAccountBase } from './discord/DiscordAccountBase';
import { WynnAccountBase } from './wynn/WynnAccountBase';

export interface UserAccountBase {
    userId: string;
    credentials: UserCredentialsBase;
    wynnAccount: WynnAccountBase;
    discordAccount: DiscordAccountBase;
    ambrosiaAccount: AmbrosiaAccountBase;
}
