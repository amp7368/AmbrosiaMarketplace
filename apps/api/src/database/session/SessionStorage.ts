import { minutesToMillis } from '@appleptr16/utilities';
import { ServerProfile } from '../entity/user/UserAccount.entity';
import { Session } from './Session';

export class SessionStore {
    private static TRIM_INTERVAL: number = minutesToMillis(1);
    private sessions: Map<string, Session> = new Map();
    private nextTrimTime = Date.now() + SessionStore.TRIM_INTERVAL;
    constructor() {
        this.isSessionValid = this.isSessionValid.bind(this);
        this.newSession = this.newSession.bind(this);
    }
    verifyTrimmed() {
        if (this.nextTrimTime > Date.now()) return;
        this.sessions.forEach((session, key, map) => {
            if (!session.isValid()) map.delete(key);
        });
    }
    newSession(user: ServerProfile): Session {
        this.verifyTrimmed();
        const session = new Session(user);
        this.sessions.set(session.sessionToken, session);
        return session;
    }
    isSessionValid(sessionToken: string): boolean {
        this.verifyTrimmed();
        const session: Session = this.sessions.get(sessionToken);
        if (!session) return false;
        return session.isValid();
    }
}

export const sessionStore = new SessionStore();
