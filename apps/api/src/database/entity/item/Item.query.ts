import { getManager } from 'typeorm';
import { AmbrosiaQuery } from '../../AmbrosiaQuery';
import { ItemRef } from './ref/ItemRef.entity';

export class ItemQuery extends AmbrosiaQuery {
    async isItemExists(instUUID: string) {
        return await getManager()
            .findByIds(ItemRef, [instUUID])
            .then(() => true)
            .catch(() => false);
    }
}
export const itemQuery = new ItemQuery();
