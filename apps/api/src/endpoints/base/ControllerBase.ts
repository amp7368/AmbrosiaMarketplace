import { ValidateBase } from './ValidateBase';
import { ExceptionFactory } from './ExceptionFactory';

export class ControllerBase<Validator extends ValidateBase<ExceptionFactory>> {
    constructor(protected validator: Validator) {}
    protected validate() {
        return this.validator;
    }
}
