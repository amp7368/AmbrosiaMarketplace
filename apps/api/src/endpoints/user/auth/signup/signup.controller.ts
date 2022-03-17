import { SignupRequest, SignupResponse } from '@api/io-model';
import { Body, Controller, Post } from '@nestjs/common';

import { Session } from '../../../../database/session/Session';
import { EndpointUrls } from '../../../EndpointUrls';
import { AuthService } from '../auth.service';
import { authExceptionFactory } from '../AuthExceptionFactory';
import { ControllerBase } from './../../../base/ControllerBase';
import { ValidateSignup } from './ValidateSignup';

@Controller(EndpointUrls.user.auth.signup.url)
export class SignupController extends ControllerBase<ValidateSignup> {
    constructor(private authService: AuthService, validator: ValidateSignup) {
        super(validator);
    }

    @Post()
    async signup(@Body() request: SignupRequest): Promise<SignupResponse> {
        if (!request) throw authExceptionFactory.badRequest(request);
        const hasUser = this.authService.hasUser(request);
        return hasUser.then((hasUser) => this.signupGotUser(request, hasUser));
    }

    private async signupGotUser(
        request: SignupRequest,
        hasUser: boolean
    ): Promise<SignupResponse> {
        // if the user exists, throw an error
        if (hasUser) throw authExceptionFactory.userAlreadyExists();
        const session = await this.authService.newSessionFromNewUser(request);
        return this.signupMadeUser(session);
    }

    private signupMadeUser(session: Session): SignupResponse {
        return new SignupResponse(session);
    }
}
