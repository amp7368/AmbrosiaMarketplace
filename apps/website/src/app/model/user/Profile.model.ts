import { ProfileBase } from '@api/io-model';
import { EntityState } from '@datorama/akita';

export interface Profile extends ProfileBase {}
export type ProfileState = EntityState<Profile, number>;
