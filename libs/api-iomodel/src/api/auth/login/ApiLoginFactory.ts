import { EmptyObject } from '@appleptr16/utilities';
import { ApiFactory, ApiFactoryObj } from '../../ApiFactory';
import { apiAuthFactory, AuthRequest, AuthResponse } from '../ApiAuthFactory';
import { ILoginId } from '../LoginId';
import { SessionBase } from '../SessionBase';

export type LoginRequest = ApiFactoryObj<EmptyObject, AuthRequest, ILoginId>;
export type LoginResponse = ApiFactoryObj<
    { expiration: Date },
    AuthResponse,
    SessionBase
>;
export const apiLoginFactory: ApiFactory<LoginRequest, LoginResponse> =
    new ApiFactory<LoginRequest, LoginResponse>(
        apiAuthFactory,
        (): LoginRequest['supply'] => ({}),
        (): LoginResponse['supply'] => ({
            expiration: new Date(Date.now() + 500000),
        })
    );
