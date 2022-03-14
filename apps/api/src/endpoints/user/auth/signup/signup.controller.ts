import { RequestSignup, ResponseSignup } from '@api/io-model';
import { isFalsey } from '@appleptr16/utilities';
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
    async signup(@Body() request: RequestSignup): Promise<ResponseSignup> {
        if (!request) throw authExceptionFactory.badRequest(request);
        const hasUser = this.authService.hasUser(request);
        return hasUser.then((hasUser) => this.signupGotUser(request, hasUser));
    }

    private async signupGotUser(
        request: RequestSignup,
        hasUser: boolean
    ): Promise<ResponseSignup> {
        // if the user exists, throw an error
        if (hasUser) throw authExceptionFactory.userAlreadyExists();
        const session = await this.authService.newSessionFromNewUser(request);
        return this.signupMadeUser(session);
    }

    private signupMadeUser(session: Session): ResponseSignup {
        return new ResponseSignup(session);
    }
}
