import { ILoginId } from '../../api/auth/LoginId';

export interface CredentialsBase extends ILoginId {
    email?: string;
    phone?: number;
}
