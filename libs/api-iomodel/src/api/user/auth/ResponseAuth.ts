import { AmbrosiaResponse } from '../../AmbrosiaResponse';
import { SessionBase } from './SessionBase';

export class ResponseAuth extends AmbrosiaResponse {
    sessionToken: string;
    expiration: number;
    constructor(session: SessionBase) {
        super();
        this.sessionToken = session.sessionToken;
        this.expiration = session.expiration;
    }
}
