import { ItemRefBase, ItemSlotsBase } from '@api/io-model';
import { CreateClassFactory } from '@appleptr16/utilities';
import { Entity, OneToMany, PrimaryGeneratedColumn } from 'typeorm';
import { ItemRef } from './ItemRef.entity';

@Entity()
export class ItemSlots implements ItemSlotsBase {
    static factory = new CreateClassFactory(ItemSlots, () => ({ items: [] }));
    static create = ItemSlots.factory.createFn();

    @PrimaryGeneratedColumn({ type: 'bigint' })
    id: number;

    @OneToMany(() => ItemRef, (item) => item.instUUID)
    items: ItemRefBase[];
}
