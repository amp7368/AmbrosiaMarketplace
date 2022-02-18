import { AmbrosiaResponse } from '../../AmbrosiaResponse';
export class ResponseItem extends AmbrosiaResponse {
    instUUID: string;
    constructor(instUUID: string) {
        super();
        this.instUUID = instUUID;
    }
}
