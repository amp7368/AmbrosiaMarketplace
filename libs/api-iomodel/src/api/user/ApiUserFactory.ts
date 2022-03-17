import { AmbrosiaRequest } from '@api/io-model';
import { EmptyObject } from '@appleptr16/utilities';

import { AmbrosiaResponse, apiAmbrosiaFactory } from '../ApiAmbrosiaFactory';
import { ApiFactory, ApiFactoryObj } from '../ApiFactory';

export type UserRequest = ApiFactoryObj<
    EmptyObject,
    AmbrosiaRequest,
    { sessionToken: string }
>;
export type UserResponse = ApiFactoryObj<
    EmptyObject,
    AmbrosiaResponse,
    EmptyObject
>;

export const apiUserFactory: ApiFactory<UserRequest, UserResponse> =
    new ApiFactory(
        apiAmbrosiaFactory,
        (): UserRequest['supply'] => ({}),
        (): UserResponse['supply'] => ({})
    );
