import { Module, UsePipes } from '@nestjs/common';
import { ItemController } from './item.controller';

@Module({
    controllers: [ItemController],
    providers: [],
})
export class ItemModule {}
