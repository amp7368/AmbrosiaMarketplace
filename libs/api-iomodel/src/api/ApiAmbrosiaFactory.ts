import { EmptyObject } from '@appleptr16/utilities';

import {
    apiEmptyFactory,
    ApiFactory,
    ApiFactoryObj,
    EmptyApiFactoryObj,
} from './ApiFactory';

export type AmbrosiaRequest = ApiFactoryObj<
    EmptyObject,
    EmptyApiFactoryObj,
    EmptyObject
>;
export type AmbrosiaResponse = ApiFactoryObj<
    { timestamp: Date },
    EmptyApiFactoryObj,
    EmptyObject
>;

export const apiAmbrosiaFactory: ApiFactory<AmbrosiaRequest, AmbrosiaResponse> =
    new ApiFactory<AmbrosiaRequest, AmbrosiaResponse>(
        apiEmptyFactory,
        (): AmbrosiaRequest['supply'] => ({}),
        (): AmbrosiaResponse['supply'] => ({
            timestamp: new Date(),
        })
    );
