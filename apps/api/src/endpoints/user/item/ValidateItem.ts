import {
    RequestBuy,
    RequestFoundLoot,
    RequestRegisterItem,
    RequestSell,
    RequestItem,
} from '@api/io-model';
import { ValidateUser } from '../ValidateUser';
import { itemQuery } from '../../../database/entity/item/Item.query';
import {
    ItemExceptionFactory,
    itemExceptionFactory,
} from './ItemExceptionFactory';

export class ValidateItem extends ValidateUser<ItemExceptionFactory> {
    async validateSell(request: RequestSell) {
        this.preValidate(request);
        await this.validateItemExists(request);
    }
    async validateBuy(request: RequestBuy) {
        this.preValidate(request);
        this.validateItemExists(request);
    }
    async validateFoundLoot(request: RequestFoundLoot) {
        this.preValidate(request);
        this.validateItemExists(request);
    }
    async validateRegisterItem(request: RequestRegisterItem) {
        this.preValidate(request);
    }
    async validateItemExists(request: RequestItem) {
        const isExists: boolean = await itemQuery.isItemExists(
            request.instUUID
        );
        if (!isExists) this.exception().itemNotFound();
    }
}
export const validateItem = new ValidateItem(itemExceptionFactory);
