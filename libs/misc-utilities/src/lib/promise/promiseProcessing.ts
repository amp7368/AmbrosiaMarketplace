type BiFunction<T, V, R> = (arg1: T, arg2: V) => R;
type MonoFunction<T, R> = (arg: T) => R;

export async function processPromise1<T, V, R>(
    arg1: T,
    promise: Promise<V>,
    fn: BiFunction<T, V, Promise<R>>
): Promise<R> {
    return promise.then(passArg(arg1, fn));
}

export async function processPromise2<T, V, W, R>(
    arg1: T,
    promise: Promise<V>,
    fn1: BiFunction<T, V, Promise<W>>,
    fn2: BiFunction<V, W, Promise<R>>
): Promise<R> {
    const arg2 = await promise;
    const arg3 = await fn1(arg1, arg2);
    return fn2(arg2, arg3);
}
export async function mapPromise1<T, R>(
    promise: Promise<T>,
    fn1: MonoFunction<T, Promise<R>>
): Promise<R> {
    return promise.then(fn1);
}
export async function mapPromise2<T, V, R>(
    promise: Promise<T>,
    fn1: MonoFunction<T, Promise<V>>,
    fn2: MonoFunction<V, Promise<R>>
): Promise<R> {
    return promise.then(fn1).then(fn2);
}
export async function mapPromise3<T, V, W, R>(
    promise: Promise<T>,
    fn1: MonoFunction<T, Promise<V>>,
    fn2: MonoFunction<V, Promise<W>>,
    fn3: MonoFunction<W, Promise<R>>
): Promise<R> {
    return promise.then(fn1).then(fn2).then(fn3);
}
export function passArg<T, V, R>(
    arg1: T,
    fn: BiFunction<T, V, R>
): MonoFunction<V, R> {
    return (arg2: V) => fn(arg1, arg2);
}
