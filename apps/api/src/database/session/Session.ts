import { v4 } from 'uuid';
import { hoursToMillis } from '@misc/for-now';
import { UserAccount } from '../entity/user/UserAcount.entity';

export class Session {
    static SESSION_EXPIRATION = hoursToMillis(1);

    user: UserAccount;
    sessionToken: string = v4();
    expiration: number = Date.now() + Session.SESSION_EXPIRATION;

    constructor(user: UserAccount) {
        this.user = user;
    }

    refresh() {
        this.expiration = Date.now() + Session.SESSION_EXPIRATION;
    }

    isValid(): boolean {
        return Date.now() <= this.expiration;
    }
}
