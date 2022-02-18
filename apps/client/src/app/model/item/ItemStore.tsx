import { EntityState, EntityStore, StoreConfig } from '@datorama/akita';
export class StringWrapper {
    str: string;
    isDefault: boolean;
    constructor(json: JSON) {
        this.str = json.parse('val');
        this.isDefault = json.parse('isDefault');
    }
}
export class ItemMeta {
    name: StringWrapper;
    constructor(json: JSON) {
        this.name = json.parse('name');
    }
}
export class ItemData {
    meta: ItemMeta;
    constructor(json: JSON) {
        this.meta = new ItemMeta(json.parse('itemMeta'));
    }
}

// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface ItemState extends EntityState<ItemData, number> {}
@StoreConfig({ name: 'item' })
export class ItemStore extends EntityStore<ItemData> {}
