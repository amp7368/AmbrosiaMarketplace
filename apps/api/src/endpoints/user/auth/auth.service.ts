import { ILoginId, SignupRequest, UserAccountBase } from '@api/io-model';
import { UserAccount } from '../../../database/entity/user/UserAcount.entity';
import { Session } from '../../../database/session/Session';
import { sessionStore } from '../../../database/session/SessionStorage';
import { userAccountQuery } from '../../../database/entity/user/userAccount.query';

export class AuthService {
    isOkLogin(
        user: UserAccountBase | undefined,
        credentials: ILoginId
    ): boolean {
        if (!user) return false;
        return user.credentials.password === credentials.password;
    }

    async getUser(credentials: ILoginId): Promise<UserAccount> {
        return userAccountQuery.getUser(credentials.username);
    }

    async hasUser(credentials: ILoginId): Promise<boolean> {
        return this.getUser(credentials)
            .then((user) => !!user)
            .catch(() => false);
    }

    async newUser(signup: SignupRequest): Promise<UserAccount> {
        return userAccountQuery.newUser(signup);
    }
    async newSessionFromNewUser(signup: SignupRequest): Promise<Session> {
        return this.newUser(signup).then(sessionStore.newSession);
    }
}
