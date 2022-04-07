import { ValidateBase } from '../base/ValidateBase';
import {
    imageExceptionFactory,
    ImageExceptionFactory,
} from './ImageExceptionFactory';

export class ValidateImage extends ValidateBase<ImageExceptionFactory> {
    constructor() {
        super(imageExceptionFactory);
    }
    verifyFile(file: Express.Multer.File) {}
    override preValidate(request: unknown): void {}
}
