import { InitDb } from '../../InitDb';
import { ItemRef } from './ref/ItemRef.entity';
import { ItemSlots } from './ref/ItemSlots.entity';
import { ItemIdentified } from './identified/ItemIdentified.entity';
import { historyInitDb } from './history/history.initDb';

class ItemInitDb extends InitDb {
    getEntities() {
        return [
            ItemRef,
            ItemSlots,
            ItemIdentified,
            ...historyInitDb.getEntities(),
        ];
    }
}
export const itemInitDb = new ItemInitDb();
