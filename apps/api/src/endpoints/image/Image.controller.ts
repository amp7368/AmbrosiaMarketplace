import {
    Controller,
    Post,
    UploadedFile,
    UseInterceptors,
} from '@nestjs/common';
import { FileInterceptor } from '@nestjs/platform-express';

import { ControllerBase } from '../base/ControllerBase';
import { EndpointUrls } from '../EndpointUrls';
import { ValidateImage } from './ValidateImage';
import { apiImageFactory } from '../../../../../libs/api-iomodel/src/api/image/ApiImageFactory';

@Controller(EndpointUrls.user.auth.login.url)
export class ImageController extends ControllerBase<ValidateImage> {
    @Post()
    @UseInterceptors(FileInterceptor('file'))
    uploadFile(@UploadedFile() file: Express.Multer.File) {
        this.validate(file, ['verifyFile']);
        console.log(file);
        return apiImageFactory.response({});
    }
}
