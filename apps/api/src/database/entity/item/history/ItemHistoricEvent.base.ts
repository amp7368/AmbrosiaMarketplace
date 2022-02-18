import { Column, ManyToOne, PrimaryGeneratedColumn } from 'typeorm';
import { UserId } from '../../userId/UserId.entity';
import { EventToHistoryRelation } from './EventToHistoryRelation';
import { ItemHistoricEventProps } from './ItemHistoricEventProps';

export abstract class ItemHistoricEvent {
    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    dateReported: number = Date.now();

    @Column()
    transactionDate: number;

    @Column(() => EventToHistoryRelation, { prefix: false })
    history: EventToHistoryRelation;

    @ManyToOne(() => UserId, (userId) => userId.uuid)
    userId: string;

    assignProps(props: ItemHistoricEventProps) {
        this.transactionDate = props.transactionDate;
    }
}
