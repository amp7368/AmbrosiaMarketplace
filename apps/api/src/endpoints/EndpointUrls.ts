import endpoints from './Endpoints.json';

type Endpoint = {
    url: string;
} & { [key: string]: Endpoint };

function normalize(endpoint: string): string {
    let newUrl = '';
    let wasADash = false;
    for (const ch of endpoint) {
        if (wasADash) {
            newUrl += ch.toUpperCase();
            wasADash = false;
        } else if (ch === '-') {
            wasADash = true;
        } else {
            newUrl += ch;
        }
    }
    return newUrl;
}
function addParent(endpoints: any, parent: string): Endpoint {
    for (const endpoint in endpoints) {
        const url = `${parent}/${endpoint}`;
        const children = endpoints[endpoint];
        addParent(children, url);
        endpoints[normalize(endpoint)] = { url, ...children };
    }
    return endpoints;
}
export const EndpointUrls: Endpoint = addParent(endpoints, '');
