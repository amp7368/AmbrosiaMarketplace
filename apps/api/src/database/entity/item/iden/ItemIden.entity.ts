import { ItemIdenBase } from '@api/io-model';
import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';
import { ItemMeta } from './ItemMeta';
import { ItemPowder } from './ItemPowder';

@Entity()
export class ItemIden implements ItemIdenBase {
    @PrimaryGeneratedColumn('uuid')
    instUUID: string;

    @Column(() => ItemMeta)
    itemMeta: ItemMeta;

    @Column(() => ItemPowder)
    powder?: any;
}
