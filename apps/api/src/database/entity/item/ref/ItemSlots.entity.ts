import { ItemRefBase, ItemSlotsBase } from '@api/io-model';
import { Entity, OneToMany, PrimaryGeneratedColumn } from 'typeorm';
import { ItemRef } from './ItemRef.entity';

@Entity()
export class ItemSlots implements ItemSlotsBase {
    @PrimaryGeneratedColumn({ type: 'bigint' })
    id: number;

    @OneToMany(() => ItemRef, (item) => item.instUUID)
    items: ItemRefBase[];
}
