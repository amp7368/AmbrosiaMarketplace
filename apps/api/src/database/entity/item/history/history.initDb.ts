// ITEM HISTORY IMPORTS FIRST
import './ItemHistory.entity';
import { InitDb } from '../../../InitDb';
import { ItemHistory } from './ItemHistory.entity';
import { ItemHistoricBuy } from './ItemHistoricBuy.entity';
import { ItemHistoricFound } from './ItemHistoricFound.entity';
import { ItemHistoricSell } from './ItemHistoricSell.entity';
// ITEM HISTORY IMPORTS FIRST
export class HistoryInitDb extends InitDb {
    getEntities() {
        return [
            ItemHistory,
            ItemHistoricBuy,
            ItemHistoricFound,
            ItemHistoricSell,
        ];
    }
}
export const historyInitDb = new HistoryInitDb();
console.log(historyInitDb.getEntities());
