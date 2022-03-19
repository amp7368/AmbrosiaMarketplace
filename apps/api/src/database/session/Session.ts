import { v4 } from 'uuid';
import { hoursToMillis } from '@appleptr16/utilities';
import { ServerProfile } from '../entity/user/UserAcount.entity';
import { SessionBase } from '@api/io-model';

export class Session implements SessionBase {
    static SESSION_EXPIRATION = hoursToMillis(1);

    user: ServerProfile;
    sessionToken: string = v4();
    expiration: Date = this.refresh();

    constructor(user: ServerProfile) {
        this.user = user;
    }

    refresh() {
        return (this.expiration = new Date(
            Date.now() + Session.SESSION_EXPIRATION
        ));
    }

    isValid(): boolean {
        return new Date() <= this.expiration;
    }
}
