import { UserExceptionFactory } from '../UserExceptionFactory';
export class ItemExceptionFactory extends UserExceptionFactory {
    itemNotFound() {
        this.conflict('Item does not exist');
    }
}
export const itemExceptionFactory: ItemExceptionFactory =
    new ItemExceptionFactory();
