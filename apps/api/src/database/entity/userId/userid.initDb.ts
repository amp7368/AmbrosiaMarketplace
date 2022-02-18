import { InitDb } from '../../InitDb';
import { UserId } from './UserId.entity';

export class UserIdInitDb extends InitDb {
    getEntities() {
        return [UserId];
    }
}
export const userIdInitDb = new UserIdInitDb();
