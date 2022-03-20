import { Multer } from 'multer';
import {
    INestApplication,
    Module,
    NestApplicationOptions,
    ValidationPipe,
} from '@nestjs/common';
import { NestFactory } from '@nestjs/core';

import { initTypeOrmDbConnection } from './database/initDbConnection';
import { AuthModule } from './endpoints/user/auth/auth.module';
import { ItemModule } from './endpoints/user/item/item.module';
import { PingModule } from './endpoints/ping/ping.module';

const PORT = 80;

@Module({
    imports: [PingModule, AuthModule, ItemModule],
})
class AppModule {}

async function bootstrap() {
    await initTypeOrmDbConnection();

    const nestOptions: NestApplicationOptions = {
        cors: true,
    };
    const pipes = new ValidationPipe({
        // whitelist: true,
        enableDebugMessages: true,
    });
    const nestApp: INestApplication = await NestFactory.create(
        AppModule,
        nestOptions
    );

    await nestApp.useGlobalPipes(pipes).listen(PORT, () => {
        console.log(`Nest server running on port ${PORT}`);
    });
}
bootstrap();
