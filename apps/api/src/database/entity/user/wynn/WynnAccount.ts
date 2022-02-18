import { WynnAccountBase } from '@api/io-model';
import { Column } from 'typeorm';
import { PlayerInventory } from './PlayerInventory';

export class WynnAccount implements WynnAccountBase {
    @Column(() => PlayerInventory)
    playerInventory: PlayerInventory;
}
