import { ValidateBase } from '../base/ValidateBase';
import { ImageExceptionFactory } from './ImageExceptionFactory';

export class ValidateImage extends ValidateBase<ImageExceptionFactory> {
    verifyFile(file: Express.Multer.File) {}
}
