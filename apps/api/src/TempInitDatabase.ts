import { apiSignupFactory } from '@api/io-model';
import { Module, OnApplicationBootstrap } from '@nestjs/common';

import { AuthService } from './endpoints/user/auth/auth.service';
import { ILoginId } from '../../../libs/api-iomodel/src/api/auth/LoginId';

@Module({
    imports: [],
    providers: [AuthService],
})
export class TempInitDatabase implements OnApplicationBootstrap {
    constructor(private authService: AuthService) {}
    onApplicationBootstrap() {
        const appleptr16: ILoginId = {
            username: 'appleptr16',
            password: 'appleptr16',
        };
        if (this.authService.hasUser(appleptr16)) return;
        
        this.authService.newUser(apiSignupFactory.request(appleptr16));
    }
}
