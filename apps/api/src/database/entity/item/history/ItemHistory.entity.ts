import {
    Entity,
    OneToMany,
    OneToOne,
    PrimaryColumn,
    RelationOptions,
} from 'typeorm';
import { ItemIden } from '../iden/ItemIden.entity';
import { ItemHistoricBuy } from './ItemHistoricBuy.entity';
import { ItemHistoricFound } from './ItemHistoricFound.entity';
import { ItemHistoricSell } from './ItemHistoricSell.entity';

const historyOptions: RelationOptions = { lazy: true };

@Entity()
export class ItemHistory {
    @PrimaryColumn('uuid')
    @OneToOne(() => ItemIden)
    instUUID: string;

    @OneToMany(
        () => ItemHistoricFound,
        (historic: ItemHistoricFound) => historic.history,
        historyOptions
    )
    finds: ItemHistoricFound[];

    @OneToMany(
        () => ItemHistoricBuy,
        (historic: ItemHistoricBuy) => historic.history
    )
    buys: ItemHistoricBuy[];

    @OneToMany(
        () => ItemHistoricSell,
        (historic: ItemHistoricSell) => historic.history,
        historyOptions
    )
    sells: ItemHistoricSell[];
}
