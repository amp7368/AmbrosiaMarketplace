import { Controller, Get } from '@nestjs/common';

interface PingResponse {
    message: string;
}
@Controller('/')
export class PingController {
    @Get()
    ping(): PingResponse {
        return { message: 'pong' };
    }
}
