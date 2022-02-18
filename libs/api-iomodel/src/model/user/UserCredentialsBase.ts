import { ILoginId } from '../../api/user/auth/LoginId';
export interface UserCredentialsBase extends ILoginId {
    email: string;
}
