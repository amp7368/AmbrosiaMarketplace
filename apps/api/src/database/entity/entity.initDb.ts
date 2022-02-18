import { InitDb } from '../InitDb';
import { userInitDb } from './user/user.initDb';
import { itemInitDb } from './item/Item.initDb';
import { userIdInitDb } from './userId/userid.initDb';

class EntityInitDb extends InitDb {
    getEntities() {
        return [
            ...userInitDb.getEntities(),
            ...itemInitDb.getEntities(),
            ...userIdInitDb.getEntities(),
        ];
    }
}
export const entityInitDb = new EntityInitDb();
