import { Store, StoreConfig } from '@datorama/akita';

export interface SessionState {
    sessionToken?: string;
    lastRefresh: number;
}
function createInitialState(): SessionState {
    return {
        lastRefresh: 0,
    };
}

@StoreConfig({ name: 'session' })
export class SessionStore extends Store<SessionState> {
    constructor() {
        super(createInitialState());
    }
}

export const sessionStore = new SessionStore();
