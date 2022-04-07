import {
    apiLoginFactory,
    apiSignupFactory,
    LoginRequest,
    LoginResponse,
    SignupRequest,
    SignupResponse,
} from '@api/io-model';
import { v4 } from 'uuid';

import { BaseAPI } from '../base/BaseAPI';
import { RequestMethod } from '../base/RequestBuilder';

export class AuthAPI extends BaseAPI {
    async signup(props: SignupRequest['input']): SignupResponse['promise'] {
        return this.newRequest()
            .url('user', 'auth', 'signup')
            .setMethod(RequestMethod.Post)
            .payload(apiSignupFactory.request(props))
            .build();
    }
    async login(props: LoginRequest['input']): LoginResponse['promise'] {
        return this.newRequest()
            .url('user', 'auth', 'login')
            .setMethod(RequestMethod.Post)
            .payload(props)
            .build();
    }
}
export const authAPI = new AuthAPI();
