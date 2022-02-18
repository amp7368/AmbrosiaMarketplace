import { ItemIdentifiedBase } from '../../../../model/item/identified/ItemIdentifiedBase';
import { RequestUser } from '../../RequestUser';

export interface RequestRegisterItem extends RequestUser {
    item: ItemIdentifiedBase;
}
