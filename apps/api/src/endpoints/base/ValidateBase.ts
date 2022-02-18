import { AmbrosiaRequest } from '@api/io-model';
import { isFalsey } from '@misc/for-now';
import { ExceptionFactory } from './ExceptionFactory';

export class ValidateBase<Ex extends ExceptionFactory> {
    constructor(public exceptionFactory: Ex) {}
    protected exception(): Ex {
        return this.exceptionFactory;
    }
    preValidate(request: AmbrosiaRequest) {
        if (isFalsey(request)) this.exception().badRequest(request);
    }
}
