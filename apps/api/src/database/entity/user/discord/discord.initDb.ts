import { InitDb } from '../../../InitDb';

class DiscordInitDb extends InitDb {
    getEntities() {
        return [];
    }
}
export const discordInitDb = new DiscordInitDb();
