import { Entity } from 'typeorm';
import { ItemHistoricEvent } from './ItemHistoricEvent.base';
import { ItemHistoricEventProps } from './ItemHistoricEventProps';

@Entity()
export class ItemHistoricSell extends ItemHistoricEvent {
    assignProps(props: ItemHistoricEventProps): void {
        super.assignProps(props);
    }
}
