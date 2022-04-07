import { ILoginId } from '@api/io-model';
import { ValidateBase } from '../../../base/ValidateBase';
import {
    authExceptionFactory,
    AuthExceptionFactory,
} from '../AuthExceptionFactory';
import { ServerProfile } from '../../../../database/entity/user/UserAccount.entity';
export class ValidateLogin extends ValidateBase<AuthExceptionFactory> {
    validateGoodLogin(credentials: ILoginId, user: ServerProfile) {
        if (!user || user.credentials.password !== credentials.password)
            throw authExceptionFactory.loginBadCredentials();
    }
}
