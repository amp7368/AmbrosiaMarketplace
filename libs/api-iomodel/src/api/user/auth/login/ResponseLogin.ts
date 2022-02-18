import { ResponseAuth } from '../ResponseAuth';
import { SessionBase } from '../SessionBase';
export class ResponseLogin extends ResponseAuth {
    constructor(session: SessionBase) {
        super(session);
    }
}
