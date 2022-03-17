import { EmptyObject } from '@appleptr16/utilities';
import { ApiFactory, ApiFactoryObj } from '../../ApiFactory';
import { apiAuthFactory, AuthRequest, AuthResponse } from '../ApiAuthFactory';
import { ILoginId } from '../LoginId';
import { SessionBase } from '../SessionBase';

export type SignupRequest = ApiFactoryObj<EmptyObject, AuthRequest, ILoginId>;
export type SignupResponse = ApiFactoryObj<
    EmptyObject,
    AuthResponse,
    SessionBase
>;
export const apiSignupFactory: ApiFactory<SignupRequest, SignupResponse> =
    new ApiFactory<SignupRequest, SignupResponse>(
        apiAuthFactory,
        (): SignupRequest['supply'] => ({}),
        (): SignupResponse['supply'] => ({})
    );
