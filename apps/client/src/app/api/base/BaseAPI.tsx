import { RequestBuilder } from './RequestBuilder';

export class BaseAPI {
    private readonly BASE = 'http://localhost:80';

    newRequest(): RequestBuilder {
        return new RequestBuilder(this.BASE);
    }
    isStatusOk(status: number): boolean {
        return status === 200;
    }
}
