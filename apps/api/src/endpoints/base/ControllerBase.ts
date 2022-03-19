import { ExceptionFactory } from './ExceptionFactory';
import { ValidateBase, Validation, ValidationKey } from './ValidateBase';

export class ControllerBase<Validator extends ValidateBase<ExceptionFactory>> {
    constructor(public validator: Validator) {}
    protected validate<Req>(
        request: Req,
        validations?: ValidationKey<Validator, Req>[]
    ): Validator {
        this.validator.preValidate(request);
        if (!validations) return this.validator;
        for (const validationKey of validations) {
            const consumer: Validation<Req> = this.validator[
                validationKey
            ] as any;
            consumer(request);
        }
        return this.validator;
    }
}
