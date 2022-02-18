import { ItemIdentifiedBase } from '@api/io-model';
import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';
import { ItemMeta } from './ItemMeta';
import { ItemPowder } from './ItemPowder';

@Entity()
export class ItemIdentified implements ItemIdentifiedBase {
    @PrimaryGeneratedColumn('uuid')
    instUUID: string;

    @Column(() => ItemMeta)
    itemMeta: ItemMeta;

    @Column(() => ItemPowder)
    powder?: any;
}