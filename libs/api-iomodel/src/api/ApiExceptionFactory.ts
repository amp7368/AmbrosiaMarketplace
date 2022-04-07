import { CreateClassFactory, Optional } from '@appleptr16/utilities';
import { HttpStatus } from '@nestjs/common';
export interface IAmbrosiaException {
    message: string;
    status: HttpStatus;
    timestamp: Date;
    extra?: Optional<string>;
}
export class AmbrosiaException implements IAmbrosiaException {
    static factory = new CreateClassFactory(AmbrosiaException);
    static create = AmbrosiaException.factory.createFn();

    message: string;
    status: HttpStatus;
    timestamp: Date;
    extra?: Optional<string>;
}
