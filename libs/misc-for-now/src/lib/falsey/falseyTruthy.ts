export function isFalsey(thing: any): boolean {
    return !isTruthy(thing);
}
export function isTruthy(thing: any): boolean {
    return !!thing;
}
