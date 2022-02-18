import { ItemMetaBase } from './ItemMetaBase';

export interface ItemIdentifiedBase {
    instUUID?: string;
    itemMeta: ItemMetaBase;
    powder?: any;
}
