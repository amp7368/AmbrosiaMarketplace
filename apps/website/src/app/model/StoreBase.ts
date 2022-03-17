import { Store } from '@datorama/akita';

export type ReflexiveFn<T> = (arg: T) => T;

export class StoreBase<State> extends Store<State> {
    updateProp<Key extends keyof State>(
        key: Key,
        update: ReflexiveFn<State[Key]>
    ) {
        this.update((selfUser: State) => ({
            ...selfUser,
            key: update(selfUser[key]),
        }));
    }
}
