export interface IdPrimBase<T> {
    val: T;
    isDefault?: boolean;
}
export type StringPrimBase = IdPrimBase<string>;
export type NumPrimBase = IdPrimBase<number>;
