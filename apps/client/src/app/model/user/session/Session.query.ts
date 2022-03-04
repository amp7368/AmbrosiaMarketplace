import { Query } from '@datorama/akita';
import { isTruthy, minutesToMillis } from '@appleptr16/utilities';
import { map, Observable } from 'rxjs';

import { SessionState, SessionStore, sessionStore } from './Session.store';

const LOGOUT_TIME = minutesToMillis(30);

export class SessionQuery extends Query<SessionState> {
    constructor(protected override store: SessionStore) {
        super(store);
    }
    session: Observable<SessionState> = this.select();

    isLoggedIn: Observable<boolean> = this.session.pipe(
        map((sess: SessionState) => {
            return (
                isTruthy(sess.sessionToken) &&
                Date.now() - sess.lastRefresh < LOGOUT_TIME
            );
        })
    );
    sessionToken: Observable<string | undefined> = this.session.pipe(
        map((sess: SessionState) => sess.sessionToken)
    );
}
export const sessionQuery = new SessionQuery(sessionStore);
