import { EmptyObject } from '@appleptr16/utilities';
// Out, but not in Supply or Parent
export type FactoryIn<Supply, Parent, Extra> = {
    [Key in keyof Extra as Key extends keyof (Supply & Parent)
        ? never
        : Key]: Extra[Key];
};

type IApiFactoryObj<SupplyParent> = {
    supply: unknown;
    supplyParent: SupplyParent;
    input: unknown;
    output: unknown;
    parent: unknown;
};
type UnknownApiFactoryObj = IApiFactoryObj<Record<keyof unknown, unknown>>;
export type EmptyApiFactoryObj = IApiFactoryObj<EmptyObject>;
export interface ApiFactoryObj<
    Supply,
    Parent extends UnknownApiFactoryObj,
    Extra
> {
    supply: Supply;
    parent: Parent;
    supplyParent: Parent['supplyParent'] & Supply;
    input: FactoryIn<Supply, Parent['supplyParent'], Extra>;
    output: Extra & Supply & Parent['supplyParent'];
    promise: Promise<Extra & Supply & Parent['supplyParent']>;
}
export class ApiFactory<
    Req extends ApiFactoryObj<ReqSupply, ReqParent, ReqOut>,
    Res extends ApiFactoryObj<ResSupply, ResParent, ResOut>,
    ReqParent extends UnknownApiFactoryObj = Req['parent'],
    ResParent extends UnknownApiFactoryObj = Res['parent'],
    ReqSupply = Req['supply'],
    ReqOut = Req['output'],
    ResSupply = Res['supply'],
    ResOut = Res['output']
> implements IApiFactory<Req, Res>
{
    constructor(
        protected parentFactory: IApiFactory<ReqParent, ResParent>,
        public supplyRequest: () => Req['supply'],
        public supplyResponse: () => Res['supply']
    ) {}

    request(input: Req['input']): Req['output'] {
        return {
            ...input,
            ...this.supplyRequestParents(),
        } as Req['output'];
    }

    response(input: Res['input']): Res['output'] {
        return {
            ...input,
            ...this.supplyResponseParents(),
        } as Res['output'];
    }
    supplyRequestParents(): Req['supplyParent'] {
        return {
            ...this.supplyRequest(),
            ...this.parentFactory.supplyRequestParents(),
        };
    }
    supplyResponseParents(): Res['supplyParent'] {
        return {
            ...this.parentFactory.supplyResponseParents(),
            ...this.supplyResponse(),
        };
    }
}

export interface IApiFactory<
    Req extends UnknownApiFactoryObj,
    Res extends UnknownApiFactoryObj
> {
    supplyRequestParents: () => Req['supplyParent'];
    supplyResponseParents: () => Res['supplyParent'];
}
export function apiEmptySupply() {
    return {};
}
export const apiEmptyFactory: IApiFactory<
    EmptyApiFactoryObj,
    EmptyApiFactoryObj
> = {
    supplyRequestParents: apiEmptySupply,
    supplyResponseParents: apiEmptySupply,
};
