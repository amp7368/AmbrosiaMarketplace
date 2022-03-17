import { ILoginId } from '../../api';

export interface CredentialsBase extends ILoginId {
    email?: string;
    phone?: number;
}
