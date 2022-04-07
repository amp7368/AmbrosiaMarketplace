import { Module } from '@nestjs/common';
import { ImageService } from './Image.service';
import { ValidateImage } from './ValidateImage';
import { ImageController } from './Image.controller';

@Module({
    controllers: [ImageController],
    providers: [ImageService, ValidateImage],
})
export class ImageModule {}
