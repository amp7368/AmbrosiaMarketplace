import { Module, OnApplicationBootstrap } from '@nestjs/common';
import { MulterModule } from '@nestjs/platform-express';

@Module({
    imports: [],
    providers: [],
})
export class MulterInit implements OnApplicationBootstrap {
    onApplicationBootstrap() {
        MulterModule.register({ dest: './uploads' });
    }
}
