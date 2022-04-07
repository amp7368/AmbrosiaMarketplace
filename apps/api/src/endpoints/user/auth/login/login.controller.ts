import { LoginRequest, LoginResponse } from '@api/io-model';
import { Body, Controller, Post } from '@nestjs/common';

import { apiLoginFactory } from '../../../../../../../libs/api-iomodel/src/api/auth/login/ApiLoginFactory';
import { ServerProfile } from '../../../../database/entity/user/UserAccount.entity';
import { Session } from '../../../../database/session/Session';
import { sessionStore } from '../../../../database/session/SessionStorage';
import { ControllerBase } from '../../../base/ControllerBase';
import { EndpointUrls } from '../../../EndpointUrls';
import { AuthService } from '../auth.service';
import { ValidateLogin } from './ValidateLogin';

@Controller(EndpointUrls.user.auth.login.url)
export class LoginController extends ControllerBase<ValidateLogin> {
    constructor(private authService: AuthService, validator: ValidateLogin) {
        super(validator);
    }

    @Post()
    async login(
        @Body() credentials: LoginRequest['output']
    ): LoginResponse['promise'] {
        const user: ServerProfile = await this.authService.getUser(credentials);
        this.validator.validateGoodLogin(credentials, user);
        const session: Session = sessionStore.newSession(user);
        return apiLoginFactory.response(session);
    }
}
