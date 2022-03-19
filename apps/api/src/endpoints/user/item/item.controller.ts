import {
    Body,
    Controller,
    NotImplementedException,
    Post,
    Put,
} from '@nestjs/common';
import { EndpointUrls } from '../../EndpointUrls';
import { ItemRef } from '../../../database/entity/item/ref/ItemRef.entity';
import { ValidateItem, validateItem } from './ValidateItem';
import { ControllerBase } from '../../base/ControllerBase';
import { queryItemIden } from '../../../database/entity/item/iden/ItemIden.query';

@Controller()
export class ItemController extends ControllerBase<ValidateItem> {
    constructor() {
        super(validateItem);
    }

    // @Post(EndpointUrls.user.item.register.url)
    // async register(
    //     @Body() request: RequestRegisterItem
    // ): Promise<ResponseRegisterItem> {
    //     this.validate().validateRegisterItem(request);
    //     const ref: ItemRef = await queryItemIden.insertItem(request.item);
    //     return new ResponseRegisterItem(ref.instUUID);
    // }

    // @Put(EndpointUrls.user.item.foundLoot.url)
    // foundLoot(request: RequestFoundLoot) {
    //     this.validate().validateFoundLoot(request);
    // }

    // @Put(EndpointUrls.user.item.buy.url)
    // buy(request: RequestBuy) {
    //     this.validate().validateBuy(request);
    // }

    // @Put(EndpointUrls.user.item.sell.url)
    // sell(request: RequestSell) {
    //     this.validate().validateSell(request);
    // }
}
