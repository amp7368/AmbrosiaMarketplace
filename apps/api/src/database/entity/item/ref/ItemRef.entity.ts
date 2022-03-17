import { ItemRefBase } from '@api/io-model';
import { Entity, ManyToOne, OneToOne, PrimaryColumn } from 'typeorm';
import { ItemIden } from '../iden/ItemIden.entity';
import { ItemSlots } from './ItemSlots.entity';

@Entity()
export class ItemRef implements ItemRefBase {
    @PrimaryColumn('uuid')
    @OneToOne(() => ItemIden)
    instUUID: string;

    @ManyToOne(() => ItemSlots, (referrer) => referrer.items)
    slots: ItemSlots;
}
