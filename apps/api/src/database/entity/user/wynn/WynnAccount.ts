import { WynnAccountBase } from '@api/io-model';
import { Column } from 'typeorm';

export class WynnAccount implements WynnAccountBase {
    @Column('uuid')
    uuid: string;
}
