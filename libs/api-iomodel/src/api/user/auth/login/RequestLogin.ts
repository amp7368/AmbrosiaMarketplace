import { ILoginId } from '../LoginId';
export class RequestLogin implements ILoginId {
    username: string;
    password: string;
}
