import { CreateClassFactory } from '@appleptr16/utilities';
import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class ItemImage {
    static factory = new CreateClassFactory(ItemImage, () => ({}));
    static create = ItemImage.factory.createFn();
    @PrimaryGeneratedColumn()
    uuid: number;

    @Column('bytea')
    image: Buffer;
}
