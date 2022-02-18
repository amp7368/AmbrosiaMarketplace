import { ILoginId } from '@api/io-model';

export class RequestSignup implements ILoginId {
    username: string;
    password: string;
    email?: string;
}
