import { InitDb } from '../../InitDb';
import { discordInitDb } from './discord/discord.initDb';
import { ServerProfile } from './UserAccount.entity';
import { Credentials } from './UserCredentials';
import { wynnInitDb } from './wynn/wynn.initDb';

export const userInitDb = new InitDb([
    ServerProfile,
    Credentials,
    ...discordInitDb.getEntities(),
    ...wynnInitDb.getEntities(),
]);
