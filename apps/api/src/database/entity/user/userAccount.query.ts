import { RequestSignup } from '@api/io-model';
import { getManager } from 'typeorm';
import { AmbrosiaQuery } from '../../AmbrosiaQuery';
import { UserAccount } from './UserAcount.entity';

export class UserAccountQuery extends AmbrosiaQuery {
    async getUser(username: string): Promise<UserAccount> {
        return await this.managerQueryBuilder(UserAccount, 'user')
            .where('user.credentials.username = :username', {
                username: username,
            })
            .getOneOrFail();
    }
    async newUser(signup: RequestSignup): Promise<UserAccount> {
        const account = new UserAccount();
        account.assignProps(signup);
        return await getManager().save(account);
    }
}
export const userAccountQuery = new UserAccountQuery();
