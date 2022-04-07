import { write } from 'fs';
import { Optional } from '../questionable/Questionable';
export type CreateClassFn<T> = (writeOver?: Partial<T>) => T;
export type CreateClassOptionalFn<T> = (
    writeOver?: Optional<Partial<T>>
) => Optional<T>;
export class CreateClassFactory<T> {
    constructor(
        protected createNew: new () => T,
        protected defaults?: () => Partial<T>
    ) {}

    createFn(): CreateClassFn<T> {
        return (writeOver?: Partial<T>): T => this.create(writeOver);
    }
    create(writeOver?: Partial<T>): T {
        let obj: T = new this.createNew();
        if (this.defaults) obj = Object.assign(obj, this.defaults());
        if (writeOver) obj = Object.assign(obj, writeOver);
        return obj;
    }
    createOptional(writeOver?: Optional<Partial<T>>): Optional<T> {
        return writeOver ? this.create(writeOver) : undefined;
    }
}
