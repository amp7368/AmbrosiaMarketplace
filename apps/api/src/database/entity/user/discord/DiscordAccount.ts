import { DiscordAccountBase } from '@api/io-model';
import { Column } from 'typeorm';

export class DiscordAccount implements DiscordAccountBase {
    @Column('bigint', { nullable: true })
    uuid: number;
}
