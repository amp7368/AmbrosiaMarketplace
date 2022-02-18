import { ItemRefBase } from '@api/io-model';
import { Entity, ManyToOne, OneToOne, PrimaryColumn } from 'typeorm';
import { ItemIdentified } from '../identified/ItemIdentified.entity';
import { ItemSlots } from './ItemSlots.entity';

@Entity()
export class ItemRef implements ItemRefBase {
    @PrimaryColumn('uuid')
    @OneToOne(() => ItemIdentified)
    instUUID: string;

    @ManyToOne(() => ItemSlots, (referrer) => referrer.items)
    slots: ItemSlots;
}
