import { SignupRequest, SignupResponse } from '@api/io-model';
import { Body, Controller, Post } from '@nestjs/common';

import { Session } from '../../../../database/session/Session';
import { EndpointUrls } from '../../../EndpointUrls';
import { AuthService } from '../auth.service';
import { authExceptionFactory } from '../AuthExceptionFactory';
import { ControllerBase } from './../../../base/ControllerBase';
import { ValidateSignup } from './ValidateSignup';
import { apiSignupFactory } from '../../../../../../../libs/api-iomodel/src/api/auth/signup/ApiSignupFactory';

@Controller(EndpointUrls.user.auth.signup.url)
export class SignupController extends ControllerBase<ValidateSignup> {
    constructor(private authService: AuthService, validator: ValidateSignup) {
        super(validator);
    }

    @Post()
    async signup(
        @Body() request: SignupRequest['output']
    ): SignupResponse['promise'] {
        this.validate(request);
        return this.authService
            .hasUser(request)
            .then((hasUser: boolean) => this.signupGotUser(request, hasUser));
    }

    private async signupGotUser(
        request: SignupRequest['output'],
        hasUser: boolean
    ): SignupResponse['promise'] {
        // if the user exists, throw an error
        if (hasUser) throw authExceptionFactory.userAlreadyExists();
        const session: Session = await this.authService.newSession(request);
        return apiSignupFactory.response(session);
    }
}
