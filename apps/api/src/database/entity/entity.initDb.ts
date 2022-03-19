import { InitDb } from '../InitDb';
import { userInitDb } from './user/user.initDb';
import { itemInitDb } from './item/Item.initDb';
import { userIdInitDb } from './userId/userid.initDb';

export const entityInitDb = new InitDb([
    ...userInitDb.getEntities(),
    ...itemInitDb.getEntities(),
    ...userIdInitDb.getEntities(),
]);
