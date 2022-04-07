import { SessionBase } from '@api/io-model';
import { dateFactory } from '@appleptr16/utilities';
import { v4 } from 'uuid';

import { ServerProfile } from '../entity/user/UserAccount.entity';

export class Session implements SessionBase {
    static EXPIRATION_MINS = 60;

    user: ServerProfile;
    sessionToken: string = v4();
    expiration: Date;

    constructor(user: ServerProfile) {
        this.user = user;
        this.user.credentials = null;
        this.refresh();
    }

    refresh() {
        this.expiration = dateFactory.fromNowMinutes(Session.EXPIRATION_MINS);
    }

    isValid(): boolean {
        return new Date() <= this.expiration;
    }
}
