import { InitDb } from '../../../InitDb';

class AmbrosiaInitDb extends InitDb {
    getEntities() {
        return [];
    }
}
export const ambrosiaInitDb = new AmbrosiaInitDb();
