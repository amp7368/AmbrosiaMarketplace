import {
    RequestLogin,
    RequestSignup,
    ResponseLogin,
    ResponseSignup,
} from '@api/io-model';

import { BaseAPI } from '../base/BaseAPI';
import { RequestMethod } from '../base/RequestBuilder';

export class AuthAPI extends BaseAPI {
    async signup(props: RequestSignup): Promise<ResponseSignup> {
        return this.newRequest()
            .url('user', 'auth', 'signup')
            .setMethod(RequestMethod.Post)
            .payload(props)
            .build();
    }
    async login(props: RequestLogin): Promise<ResponseLogin> {
        return this.newRequest()
            .url('user', 'auth', 'login')
            .setMethod(RequestMethod.Post)
            .payload(props)
            .build();
    }
}
export const authAPI = new AuthAPI();
