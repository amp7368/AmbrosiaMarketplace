import { EntityTarget } from 'typeorm';

export class InitDb<Entity> {
    constructor(private entities?: EntityTarget<Entity>[]) {}
    getEntities(): EntityTarget<Entity>[] {
        return this.entities ?? [];
    }
}
