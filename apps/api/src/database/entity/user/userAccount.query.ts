import { SignupRequest } from '@api/io-model';
import { getManager } from 'typeorm';

import { AmbrosiaQuery } from '../../AmbrosiaQuery';
import { ServerProfile } from './UserAcount.entity';
import { Credentials } from './UserCredentials';

export class UserAccountQuery extends AmbrosiaQuery {
    async getUser(username: string): Promise<ServerProfile> {
        return await this.managerQueryBuilder(ServerProfile, 'user')
            .where('user.credentials.username = :username', { username })
            .getOneOrFail();
    }
    async newUser(signup: SignupRequest['output']): Promise<ServerProfile> {
        const account: ServerProfile = new ServerProfile();
        account.credentials = Credentials.create(signup);
        return await getManager().save(account);
    }
}

export const userAccountQuery = new UserAccountQuery();
