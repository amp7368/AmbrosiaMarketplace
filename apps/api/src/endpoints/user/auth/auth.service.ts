import { ILoginId, ServerProfileBase, SignupRequest } from '@api/io-model';
import { Optional } from '@appleptr16/utilities';

import { ServerProfile } from '../../../database/entity/user/UserAccount.entity';
import { Session } from '../../../database/session/Session';
import { sessionStore } from '../../../database/session/SessionStorage';
import { userAccountQuery } from '../../../database/entity/user/UserAccount.query';

export class AuthService {
    async getUser(credentials: ILoginId): Promise<ServerProfile> {
        return userAccountQuery.getUser(credentials.username);
    }

    async hasUser(credentials: ILoginId): Promise<boolean> {
        return this.getUser(credentials)
            .then((user: ServerProfile) => !!user)
            .catch(() => false);
    }

    async newUser(signup: SignupRequest['output']): Promise<ServerProfile> {
        return userAccountQuery.newUser(signup);
    }
    async newSession(signup: SignupRequest['output']): Promise<Session> {
        return this.newUser(signup).then(sessionStore.newSession);
    }
}
