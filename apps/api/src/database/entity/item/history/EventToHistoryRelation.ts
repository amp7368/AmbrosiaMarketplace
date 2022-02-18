import { ManyToOne } from 'typeorm';
import { ItemHistory } from './ItemHistory.entity';

export class EventToHistoryRelation {
    @ManyToOne(() => ItemHistory)
    history: ItemHistory;
}
