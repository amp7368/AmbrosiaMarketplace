// ITEM HISTORY IMPORTS FIRST
import './ItemHistory.entity';
import { InitDb } from '../../../InitDb';
import { ItemHistory } from './ItemHistory.entity';
import { ItemHistoricBuy } from './ItemHistoricBuy.entity';
import { ItemHistoricFound } from './ItemHistoricFound.entity';
import { ItemHistoricSell } from './ItemHistoricSell.entity';
// ITEM HISTORY IMPORTS FIRST
export const historyInitDb = new InitDb([
    ItemHistory,
    ItemHistoricBuy,
    ItemHistoricFound,
    ItemHistoricSell,
]);
