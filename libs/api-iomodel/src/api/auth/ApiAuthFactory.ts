import { EmptyObject } from '@appleptr16/utilities';

import {
    AmbrosiaRequest,
    AmbrosiaResponse,
    apiAmbrosiaFactory,
} from '../ApiAmbrosiaFactory';
import { ApiFactory, ApiFactoryObj } from '../ApiFactory';
import { ILoginId } from './LoginId';
import { SessionBase } from './SessionBase';

export type AuthRequest = ApiFactoryObj<EmptyObject, AmbrosiaRequest, ILoginId>;
export type AuthResponse = ApiFactoryObj<
    EmptyObject,
    AmbrosiaResponse,
    SessionBase
>;

export const apiAuthFactory: ApiFactory<AuthRequest, AuthResponse> =
    new ApiFactory(
        apiAmbrosiaFactory,
        (): AuthRequest['supply'] => ({}),
        (): AuthResponse['supply'] => ({})
    );
