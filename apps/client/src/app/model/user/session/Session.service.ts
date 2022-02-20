import {
    RequestLogin,
    RequestSignup,
    ResponseAuth,
    ResponseLogin,
    ResponseSignup,
} from '@api/io-model';
import { isTruthy, runnableEmpty } from '@misc/for-now';
import { AuthAPI, authAPI } from '../../../api/auth/AuthApi';
import { SessionState, SessionStore, sessionStore } from './Session.store';

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
    async logout() {
        this.store.update((state: SessionState) => ({
            ...state,
            sessionToken: undefined,
            lastRefresh: 0,
        }));
    }
    async signup(credentials: RequestSignup): Promise<ResponseSignup> {
        const signupResponse = this.authApi.signup(credentials);
        signupResponse.then(this.authPost).catch(runnableEmpty);
        return signupResponse;
    }
    async login(credentials: RequestLogin): Promise<ResponseLogin> {
        const loginResponse = this.authApi.login(credentials);
        loginResponse.then(this.authPost).catch(runnableEmpty);
        return loginResponse;
    }
    private authPost(session: ResponseAuth) {
        const lastRefresh = session.sessionToken ? Date.now() : 0;
        this.store.update((state) => ({
            ...state,
            sessionToken: session.sessionToken,
            lastRefresh: lastRefresh,
        }));
    }
}
export const sessionService = new SessionService(sessionStore);
