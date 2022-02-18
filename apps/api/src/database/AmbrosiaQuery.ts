import { EntityTarget, getConnection, getManager, QueryBuilder } from 'typeorm';
export class AmbrosiaQuery {
    protected connQueryBuilder<T>(target: EntityTarget<T>, alias: string) {
        return getConnection().createQueryBuilder(target, alias);
    }
    protected managerQueryBuilder<T>(target: EntityTarget<T>, alias: string) {
        return getManager().createQueryBuilder(target, alias);
    }
    protected repo<T>(target: EntityTarget<T>) {
        return getManager().getRepository(target);
    }
    protected repoQueryBuilder<T>(
        target: EntityTarget<T>,
        alias?: string
    ): QueryBuilder<T> {
        return this.repo(target).createQueryBuilder(alias);
    }
}
