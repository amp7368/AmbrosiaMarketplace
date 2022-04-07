import { apiImageFactory } from '@api/io-model';
import {
    Controller,
    Post,
    UploadedFiles,
    UseInterceptors,
} from '@nestjs/common';
import { AnyFilesInterceptor } from '@nestjs/platform-express';

import { itemImageQuery } from '../../database/entity/image/ItemImage.query';
import { ControllerBase } from '../base/ControllerBase';
import { EndpointUrls } from '../EndpointUrls';
import { ValidateImage } from './ValidateImage';

@Controller(EndpointUrls.image.url)
export class ImageController extends ControllerBase<ValidateImage> {
    constructor(validateImage: ValidateImage) {
        super(validateImage);
    }
    @Post()
    @UseInterceptors(AnyFilesInterceptor())
    uploadFile(@UploadedFiles() files: Array<Express.Multer.File>) {
        this.validate(files);
        for (const file of files) {
            this.validate(file, ['verifyFile']);
            itemImageQuery.newImage(file);
            return apiImageFactory.response({});
        }
    }
}
