import { AmbrosiaAccountBase } from '@api/io-model';
import { Column } from 'typeorm';
import { ItemSlots } from '../../item/ref/ItemSlots.entity';

export class AmbrosiaAccount implements AmbrosiaAccountBase {
    @Column(() => ItemSlots)
    marketSlots: ItemSlots;
}
