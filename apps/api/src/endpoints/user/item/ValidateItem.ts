import { ValidateUser } from '../ValidateUser';
import { itemQuery } from '../../../database/entity/item/Item.query';
import {
    ItemExceptionFactory,
    itemExceptionFactory,
} from './ItemExceptionFactory';

export class ValidateItem extends ValidateUser<ItemExceptionFactory> {
    // async validateSell(request: RequestSell) {
    //      this.validateAll([])
    // }
    // async validateBuy(request: RequestBuy) {
    //     this.validateItemExists(request);
    // }
    // async validateFoundLoot(request: RequestFoundLoot) {
    //     this.validateItemExists(request);
    // }
    // async validateRegisterItem(request: RequestRegisterItem) {
    // }
    // async validateItemExists(request: RequestItem) {
    //     const isExists: boolean = await itemQuery.isItemExists(
    //         request.instUUID
    //     );
    //     if (!isExists) this.exception().itemNotFound();
    // }
}
export const validateItem = new ValidateItem(itemExceptionFactory);
