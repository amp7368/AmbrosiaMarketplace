import { ResponseAuth } from '../ResponseAuth';
import { SessionBase } from '../SessionBase';
export class ResponseSignup extends ResponseAuth {
    constructor(session: SessionBase) {
        super(session);
    }
}
