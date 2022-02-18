import {
    RequestLogin,
    RequestSignup,
    ResponseAuth,
    ResponseLogin,
    ResponseSignup,
} from '@api/io-model';
import { AuthAPI, authAPI } from '../../../api/auth/AuthApi';
import { SessionState, SessionStore, sessionStore } from './Session.store';
import { isTruthy } from '../../../../../../../libs/misc-for-now/src/lib/falsey/falseyTruthy';

export class SessionService {
    constructor(
        private readonly store: SessionStore,
        private readonly authApi: AuthAPI = authAPI
    ) {
        this.login = this.login.bind(this);
        this.logout = this.logout.bind(this);
        this.signup = this.signup.bind(this);
        this.authPost = this.authPost.bind(this);
    }
    logout() {
        this.store.update((state: SessionState) => ({
            ...state,
            sessionToken: undefined,
            lastRefresh: 0,
        }));
    }
    signup(credentials: RequestSignup): Promise<ResponseSignup> {
        const signupResponse = this.authApi.signup(credentials);
        signupResponse.then(this.authPost);
        return signupResponse;
    }
    login(credentials: RequestLogin): Promise<ResponseLogin> {
        const loginResponse = this.authApi.login(credentials);
        loginResponse.then(this.authPost);
        return loginResponse;
    }
    private authPost(session: ResponseAuth) {
        const lastRefresh = isTruthy(session.sessionToken) ? Date.now() : 0;
        this.store.update((state) => ({
            ...state,
            sessionToken: session.sessionToken,
            lastRefresh: lastRefresh,
        }));
    }
}
export const sessionService = new SessionService(sessionStore);
