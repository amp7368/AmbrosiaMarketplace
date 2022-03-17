import { AmbrosiaQuery } from '../../../AmbrosiaQuery';
import { ItemIden } from './ItemIden.entity';
import { getManager } from 'typeorm';
import { ItemIdenBase } from '@api/io-model';
import { ItemRef } from '../ref/ItemRef.entity';

export class QueryItemIden extends AmbrosiaQuery {
    async insertItem(itemBase: ItemIdenBase): Promise<ItemRef> {
        const item: ItemIden = await getManager().save(ItemIden, itemBase);
        const ref: ItemRef = new ItemRef();
        ref.instUUID = item.instUUID;
        return await getManager().save(ItemRef, ref);
    }
}
export const queryItemIden = new QueryItemIden();
