import { createConnection } from 'typeorm';
import ormconfig from '../../ormconfig.json';
import { entityInitDb } from './entity/entity.initDb';

export async function initTypeOrmDbConnection() {
    await createConnection({
        ...ormconfig,
        entities: entityInitDb.getEntities(),
    } as any);
}
