import { AmbrosiaQuery } from '../../AmbrosiaQuery';
import { ItemImage } from './ItemImage.entity';

export class ItemImageQuery extends AmbrosiaQuery {
    async newImage(file: Express.Multer.File) {
        const image = ItemImage.create({ image: file.buffer });
        return await this.repo(ItemImage).save(image);
    }
}

export const itemImageQuery = new ItemImageQuery();
