import { HttpException, HttpStatus } from '@nestjs/common';

export class ExceptionFactory {
    exception(message: string | Record<string, any>, status: HttpStatus) {
        if (typeof message === 'string') {
            message = { message: message };
        }
        message['status'] = status;
        message['timestamp'] = Date.now();
        throw new HttpException(message, status);
    }

    forbidden(message: string) {
        this.exception(message, HttpStatus.FORBIDDEN);
    }

    unauthorized(message: string) {
        this.exception(message, HttpStatus.UNAUTHORIZED);
    }

    conflict(message: string) {
        this.exception(message, HttpStatus.CONFLICT);
    }

    badRequest(request: any) {
        const response = {
            message: 'Invalid request structure',
            request: request,
        };
        this.exception(response, HttpStatus.BAD_REQUEST);
    }
}
