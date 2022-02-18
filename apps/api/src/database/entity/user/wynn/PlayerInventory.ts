import { PlayerInventoryBase } from '@api/io-model';
import { ItemSlots } from '../../item/ref/ItemSlots.entity';

export class PlayerInventory implements PlayerInventoryBase {
    contents: ItemSlots;
    featured: ItemSlots;
}
