import { ItemMetaBase } from '@api/io-model';
import { Column } from 'typeorm';
import { NullableStringPrim, NumPrim, StringPrim } from '../common/IdPrim';

export class ItemMeta implements ItemMetaBase {
    @Column(() => StringPrim)
    name: StringPrim;

    @Column(() => StringPrim)
    material: StringPrim;

    @Column(() => NullableStringPrim)
    quest: StringPrim;

    @Column(() => NullableStringPrim)
    classRequirement: StringPrim;

    @Column(() => StringPrim)
    tier: StringPrim;

    @Column(() => StringPrim)
    type: StringPrim;

    @Column(() => StringPrim)
    restrictions: StringPrim;

    @Column(() => StringPrim)
    dropType: StringPrim;

    @Column(() => NullableStringPrim)
    addedLore: StringPrim;

    @Column(() => NumPrim)
    level: NumPrim;

    @Column(() => StringPrim)
    category: StringPrim;

    @Column(() => NullableStringPrim)
    set: StringPrim;
}
