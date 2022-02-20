import { IsString } from 'class-validator';

import { ILoginId } from '../LoginId';

export class RequestSignup implements ILoginId {
    @IsString()
    username: string;
    @IsString()
    password: string;
    // @IsEmail()
    email?: string;
}
