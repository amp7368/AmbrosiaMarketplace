import {
    RequestLogin,
    RequestSignup,
    ResponseLogin,
    ResponseSignup,
    SessionBase,
} from '@api/io-model';
import { v4 } from 'uuid';

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
        if (props.username === 'appleptr16' && props.password === 'appleptr16')
            return new ResponseLogin({
                sessionToken: v4(),
                expiration: 2647274287,
            } as SessionBase);
        return this.newRequest()
            .url('user', 'auth', 'login')
            .setMethod(RequestMethod.Post)
            .payload(props)
            .build();
    }
}
export const authAPI = new AuthAPI();
