import { ExceptionFactory } from '../base/ExceptionFactory';
export class UserExceptionFactory extends ExceptionFactory {
    badSession() {
        this.unauthorized('Invalid session');
    }
}
