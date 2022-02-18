import { InitDb } from '../../../InitDb';

class WynnInitDb extends InitDb {
    getEntities() {
        return [];
    }
}
export const wynnInitDb = new WynnInitDb();
