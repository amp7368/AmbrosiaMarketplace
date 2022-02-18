import { AmbrosiaQuery } from '../../../AmbrosiaQuery';
import { ItemIdentified } from './ItemIdentified.entity';
import { getManager } from 'typeorm';
import { ItemIdentifiedBase } from '@api/io-model';
import { ItemRef } from '../ref/ItemRef.entity';

export class QueryItemIdentified extends AmbrosiaQuery {
    async insertItem(itemBase: ItemIdentifiedBase): Promise<ItemRef> {
        const item: ItemIdentified = await getManager().save(
            ItemIdentified,
            itemBase
        );
        const ref: ItemRef = new ItemRef();
        ref.instUUID = item.instUUID;
        return await getManager().save(ItemRef, ref);
    }
}
export const queryItemIdentified = new QueryItemIdentified();
