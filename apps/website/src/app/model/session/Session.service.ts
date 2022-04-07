import {
    AuthResponse,
    LoginRequest,
    LoginResponse,
    SignupRequest,
    SignupResponse,
} from '@api/io-model';
import { emptyRunnable } from '@appleptr16/utilities';

import { authAPI } from '../../api/auth/AuthApi';
import { selfUserQuery } from './SelfUser.query';
import { selfUserStore } from './SelfUser.store';

export class SessionService {
    async logout() {
        selfUserStore.setSession(undefined);
    }
    async signup(request: SignupRequest['input']): SignupResponse['promise'] {
        const response: SignupResponse['promise'] = authAPI.signup(request);
        response.then(this.authPost).catch(emptyRunnable);
        return response;
    }
    async login(request: LoginRequest['input']): LoginResponse['promise'] {
        const response: LoginResponse['promise'] = authAPI.login(request);
        response.then(this.authPost).catch(emptyRunnable);
        return response;
    }
    private authPost(response: AuthResponse['output']) {
        selfUserStore.setSession(response);
    }
}
export const sessionService = new SessionService();
