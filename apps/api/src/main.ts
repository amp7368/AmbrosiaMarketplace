import { Module, ValidationPipe } from '@nestjs/common';
import { NestFactory } from '@nestjs/core';
import { FastifyAdapter } from '@nestjs/platform-fastify';
import { initTypeOrmDbConnection } from './database/initDbConnection';
import { AuthModule } from './endpoints/user/auth/auth.module';
import { PingModule } from './endpoints/ping/ping.module';
import { ItemModule } from './endpoints/user/item/item.module';

const PORT = 80;

@Module({
    imports: [AuthModule, PingModule, ItemModule],
})
class AppModule {}
async function bootstrap() {
    await initTypeOrmDbConnection();
    const app = await NestFactory.create(AppModule, new FastifyAdapter());
    app.useGlobalPipes(new ValidationPipe({ whitelist: true }));
    await app.listen(PORT, () => {
        console.log(`Nest server running on port ${PORT}`);
    });
}
bootstrap();
