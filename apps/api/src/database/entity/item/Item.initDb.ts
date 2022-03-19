import { InitDb } from '../../InitDb';
import { ItemRef } from './ref/ItemRef.entity';
import { ItemSlots } from './ref/ItemSlots.entity';
import { ItemIden } from './iden/ItemIden.entity';
import { historyInitDb } from './history/history.initDb';

export const itemInitDb = new InitDb([
    ItemRef,
    ItemSlots,
    ItemIden,
    ...historyInitDb.getEntities(),
]);
