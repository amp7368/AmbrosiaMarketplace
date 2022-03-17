export type Predicate<T> = (test: T) => boolean;
export type Runnable = () => void;
export type Supplier<T> = () => T;
export type Consumer<T> = (arg: T) => void;
export const emptyRunnable: Runnable = () => {};
