export function uppercaseFirst<T extends string>(str: T): Capitalize<T> {
    const firstChar = str.charAt(0).toUpperCase();
    return (firstChar + str.slice(1)) as Capitalize<T>;
}
