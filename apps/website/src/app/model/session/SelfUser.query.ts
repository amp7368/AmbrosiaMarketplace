import { Optional } from '@appleptr16/utilities';
import { Observable } from 'rxjs';

import { QueryBase } from '../QueryBase';
import { SelfUser } from './SelfUser.model';
import { Session } from './Session.model';
import { selfUserStore } from './SelfUser.store';
import { Profile } from '../user/Profile.model';

export class SelfUserQuery extends QueryBase<SelfUser> {
    session: Observable<Optional<Session>> = this.selectKey('session');
    profile: Observable<Optional<Profile>> = this.selectKey('profile');

    isLoggedIn: Observable<boolean> = this.map(
        this.session,
        (session: Optional<Session>) => {
            if (session) console.log(new Date() < session?.expiration);
            return !!session && new Date() < session.expiration;
        }
    );
}
export const selfUserQuery = new SelfUserQuery(selfUserStore);
