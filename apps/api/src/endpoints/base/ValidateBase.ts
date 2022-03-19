import { Consumer } from '@appleptr16/utilities';
import { ExceptionFactory } from './ExceptionFactory';

export type Validation<Request> = Consumer<Request>;

export type ValidationKey<Validator, Request> = keyof {
    [Key in keyof Validator as Validator[Key] extends Validation<Request>
        ? Key
        : never]: Validator[Key];
};

export class ValidateBase<ExFactory extends ExceptionFactory> {
    constructor(private exceptionFactory: ExFactory) {}
    protected exception(): ExFactory {
        return this.exceptionFactory;
    }
    preValidate(request: unknown) {
        if (!request) this.exception().badRequest(request);
    }
}
