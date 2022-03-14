import { persistState } from '@datorama/akita';

export const appStorage = persistState();
export function clearAppStorage() {
    appStorage.clearStore();
    console.log('STORAGE CLEARED');
}
