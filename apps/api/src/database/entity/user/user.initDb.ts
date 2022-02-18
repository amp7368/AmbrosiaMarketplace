import { wynnInitDb } from './wynn/wynn.initDb';
import { discordInitDb } from './discord/discord.initDb';
import { InitDb } from '../../InitDb';
import { ambrosiaInitDb } from './ambrosia/ambrosia.initDb';
import { UserAccount } from './UserAcount.entity';
import { UserCredentials } from './UserCredentials';

class UserInitDb extends InitDb {
    getEntities() {
        return [
            UserAccount,
            UserCredentials,
            ...ambrosiaInitDb.getEntities(),
            ...discordInitDb.getEntities(),
            ...wynnInitDb.getEntities(),
        ];
    }
}
export const userInitDb = new UserInitDb();
