import { ExceptionFactory } from '../../base/ExceptionFactory';

export class AuthExceptionFactory extends ExceptionFactory {
    userAlreadyExists() {
        this.conflict('The username is already taken');
    }
    loginBadCredentials() {
        this.unauthorized('The username or password is invalid');
    }
}
export const authExceptionFactory = new AuthExceptionFactory();
