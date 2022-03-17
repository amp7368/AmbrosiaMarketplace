import { InitDb } from '../../InitDb';
import { ItemRef } from './ref/ItemRef.entity';
import { ItemSlots } from './ref/ItemSlots.entity';
import { ItemIden } from './iden/ItemIden.entity';
import { historyInitDb } from './history/history.initDb';

class ItemInitDb extends InitDb {
    getEntities() {
        return [ItemRef, ItemSlots, ItemIden, ...historyInitDb.getEntities()];
    }
}
export const itemInitDb = new ItemInitDb();
