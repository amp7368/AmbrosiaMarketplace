import { Module } from '@nestjs/common';
import { SignupController } from './signup.controller';
import { AuthService } from '../auth.service';
import { ValidateSignup } from './ValidateSignup';

@Module({
    controllers: [SignupController],
    providers: [AuthService, ValidateSignup],
})
export class SignupModule {}
