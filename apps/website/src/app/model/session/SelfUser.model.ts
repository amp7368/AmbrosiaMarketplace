import { Optional } from '@appleptr16/utilities';

import { Profile } from '../user/Profile.model';
import { Session } from './Session.model';

export interface SelfUser {
    profile: Optional<Profile>;
    session: Optional<Session>;
}
