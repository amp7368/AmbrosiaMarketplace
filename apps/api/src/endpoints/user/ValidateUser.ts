import { UserRequest } from '@api/io-model';
import { sessionStore } from '../../database/session/SessionStorage';
import { ValidateBase } from '../base/ValidateBase';
import { UserExceptionFactory } from './UserExceptionFactory';

export class ValidateUser<
    Ex extends UserExceptionFactory
> extends ValidateBase<Ex> {
    validateValidSession(request: UserRequest['output']) {
        if (!sessionStore.isSessionValid(request.sessionToken)) {
            this.exception().badSession();
        }
    }
}
