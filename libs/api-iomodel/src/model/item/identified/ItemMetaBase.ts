import { NumPrimBase, StringPrimBase } from '../common/IdPrim';

export interface ItemMetaBase {
    name: StringPrimBase;
    material: StringPrimBase;
    quest: StringPrimBase;
    classRequirement: StringPrimBase;
    tier: StringPrimBase;
    type: StringPrimBase;
    restrictions: StringPrimBase;
    dropType: StringPrimBase;
    addedLore: StringPrimBase;
    level: NumPrimBase;
    category: StringPrimBase;
    set: StringPrimBase;
}
