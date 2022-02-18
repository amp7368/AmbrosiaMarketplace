import { RequestLogin, ResponseLogin } from '@api/io-model';
import { Body, Controller, Post } from '@nestjs/common';
import { UserAccount } from '../../../../database/entity/user/UserAcount.entity';
import { sessionStore } from '../../../../database/session/SessionStorage';
import { ControllerBase } from '../../../base/ControllerBase';
import { EndpointUrls } from '../../../EndpointUrls';
import { AuthService } from '../auth.service';
import { authExceptionFactory } from '../AuthExceptionFactory';
import { ValidateLogin } from './ValidateLogin';

@Controller(EndpointUrls.user.auth.login.url)
export class LoginController extends ControllerBase<ValidateLogin> {
    constructor(private authService: AuthService, validator: ValidateLogin) {
        super(validator);
        this.loginGotUser = this.loginGotUser.bind(this);
    }

    @Post()
    async login(@Body() credentials: RequestLogin): Promise<ResponseLogin> {
        const getUser: Promise<UserAccount> =
            this.authService.getUser(credentials);
        return await getUser
            .then((user) => this.loginGotUser(credentials, user))
            .catch(() => {
                throw authExceptionFactory.loginBadCredentials();
            });
    }

    private loginGotUser(
        credentials: RequestLogin,
        user: UserAccount
    ): ResponseLogin {
        const isOkLogin = this.authService.isOkLogin(user, credentials);
        if (!isOkLogin) throw authExceptionFactory.loginBadCredentials();
        const session = sessionStore.newSession(user);
        return new ResponseLogin(session);
    }
}
