export function hoursToMillis(hours: number) {
    return minutesToMillis(hours * 60);
}
export function minutesToMillis(minutes: number) {
    return secondsToMillis(minutes * 60);
}
export function secondsToMillis(seconds: number) {
    return seconds * 1000;
}
