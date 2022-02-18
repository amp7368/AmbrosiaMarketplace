import endpoints from './Endpoints.json';
interface Endpoint {
    url: string;
}
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
function addParent(endpoints: any, parent: string) {
    for (const endpoint in endpoints) {
        const url = `${parent}/${endpoint}`;
        const children = endpoints[endpoint];
        addParent(children, url);
        endpoints[normalize(endpoint)] = { url: url, ...children };
    }
    return endpoints;
}
export const EndpointUrls: Endpoint & any = addParent(endpoints, '');
