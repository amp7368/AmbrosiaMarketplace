import { AmbrosiaRequest } from '@api/io-model';
import { EmptyObject } from '@appleptr16/utilities';

import { AmbrosiaResponse, apiAmbrosiaFactory } from '../ApiAmbrosiaFactory';
import { ApiFactory, ApiFactoryObj } from '../ApiFactory';

export type ImageRequest = ApiFactoryObj<
    EmptyObject,
    AmbrosiaRequest,
    EmptyObject
>;
export type ImageResponse = ApiFactoryObj<
    EmptyObject,
    AmbrosiaResponse,
    EmptyObject
>;

export const apiImageFactory: ApiFactory<ImageRequest, ImageResponse> =
    new ApiFactory<ImageRequest, ImageResponse>(
        apiAmbrosiaFactory,
        (): ImageRequest['supply'] => ({}),
        (): ImageResponse['supply'] => ({})
    );
