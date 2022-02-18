import { IdPrimBase } from '@api/io-model';
import { Column } from 'typeorm';

export class StringPrim implements IdPrimBase<string> {
    @Column()
    val: string;
    isDefault?: boolean;
}
export class NullableStringPrim implements IdPrimBase<string> {
    @Column({ nullable: true })
    val: string;
    isDefault?: boolean;
}
export class NumPrim implements IdPrimBase<number> {
    @Column()
    val: number;
    isDefault?: boolean;
}
export class NullableNumPrim implements IdPrimBase<number> {
    @Column({ nullable: true })
    val: number;
    isDefault?: boolean;
}
