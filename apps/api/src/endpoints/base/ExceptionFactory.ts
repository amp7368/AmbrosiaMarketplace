import { AmbrosiaException } from '@api/io-model';
import { HttpException, HttpStatus } from '@nestjs/common';

export class ExceptionFactory {
    exception(message: string, status: HttpStatus, extra?: string) {
        const response: AmbrosiaException = {
            message,
            extra,
            status,
            timestamp: new Date(),
        };
        throw new HttpException(response, status);
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
        this.exception(
            'Invalid request structure',
            HttpStatus.BAD_REQUEST,
            request
        );
    }
}
