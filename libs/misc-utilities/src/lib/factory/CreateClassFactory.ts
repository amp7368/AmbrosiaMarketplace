import { Optional } from '../questionable/Questionable';
export type CreateClassFn<T> = (writeOver: Partial<T>) => T;
export type CreateClassOptionalFn<T> = (
    writeOver: Optional<Partial<T>>
) => Optional<T>;
export class CreateClassFactory<T> {
    constructor(protected createNew: new () => T) {}
    create(writeOver: Partial<T>): T {
        return Object.assign(new this.createNew(), writeOver);
    }
    createOptional(writeOver: Optional<Partial<T>>): Optional<T> {
        return writeOver ? this.create(writeOver) : undefined;
    }
}
