import { Controller } from '@nestjs/common';
import { InventoryService } from './inventory.service';
import { EndpointUrls } from '../../../EndpointUrls';

@Controller(EndpointUrls.user.display.inventory.url)
export class InventoryController {
    constructor(private inventoryService: InventoryService) {}
}
