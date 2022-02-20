import { IsString } from 'class-validator';

import { ILoginId } from '../LoginId';

export class RequestLogin implements ILoginId {
    @IsString()
    username: string;
    @IsString()
    password: string;
}
