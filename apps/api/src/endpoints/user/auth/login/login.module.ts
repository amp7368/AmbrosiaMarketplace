import { Module } from '@nestjs/common';
import { LoginController } from './login.controller';
import { AuthService } from '../auth.service';
import { ValidateLogin } from './ValidateLogin';

@Module({
    controllers: [LoginController],
    providers: [AuthService, ValidateLogin],
})
export class LoginModule {}
