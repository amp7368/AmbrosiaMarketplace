import { UserId } from '../../userId/UserId.entity';

export interface ItemHistoricEventProps {
    transactionDate: number;
    reporterUser: UserId;
}
