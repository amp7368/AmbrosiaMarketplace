import axios from 'axios';

export enum RequestMethod {
    Get = 'get',
    Post = 'post',
}
export interface RequestParam {
    key: string;
    val: string;
}

export class RequestBuilder {
    private urlField: string[] = [];
    private queryParams: RequestParam[] = [];
    private body: any = {};
    private requestMethod: RequestMethod = RequestMethod.Get;
    constructor(baseUrl: string) {
        this.urlField.push(baseUrl);
        this.url = this.url.bind(this);
        this.queryParam = this.queryParam.bind(this);
        this.payload = this.payload.bind(this);
        this.setMethod = this.setMethod.bind(this);
        this.build = this.build.bind(this);
        this.serializeQueryParams = this.serializeQueryParams.bind(this);
        this.buildUrl = this.buildUrl.bind(this);
    }
    url(url: string, ...additionalURL: string[]): RequestBuilder {
        this.urlField.push(url);
        for (const additionUrl of additionalURL) {
            this.urlField.push(additionUrl);
        }
        return this;
    }
    queryParam(key: string, val: string): RequestBuilder {
        this.queryParams.push({ key: key, val: val });
        return this;
    }
    payload(payload: any): RequestBuilder {
        this.body = payload;
        return this;
    }
    setMethod(method: RequestMethod) {
        this.requestMethod = method;
        return this;
    }
    async build(): Promise<any> {
        return axios(this.buildUrl())
            .then((response) => {
                return response.data;
            })
            .catch((error: { request: XMLHttpRequest }) => {
                let msg = JSON.parse(error.request.response);
                try {
                    msg = msg['message'] ?? msg;
                    // eslint-disable-next-line no-empty
                } catch (ignored) {}
                throw new Error(msg);
            });
    }

    private serializeQueryParams(param: RequestParam) {
        return `${param.key}=${param.val}`;
    }

    private buildUrl() {
        let url: string = this.urlField.join('/');
        const params: string = this.queryParams
            .map((queryParam) => this.serializeQueryParams(queryParam))
            .join('&');
        url = `${url}?${params}`;
        return {
            method: this.requestMethod,
            url: url,
            data: this.body,
        };
    }
}
