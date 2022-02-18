import { from, map, Observable } from 'rxjs';
import axios, { AxiosResponse } from 'axios';
import { ItemDisplayGrid } from '../../item/ItemDisplayGrid';
import { ItemData } from '../../../model/item/ItemStore';

function getItems(url: string): Observable<JSX.Element> {
    return from(axios.get(url)).pipe(map(mapResponseToItems));
}

function mapResponseToItems(response: AxiosResponse<any, any>) {
    const responseItems: JSON[] = response.data['items'];
    const items = responseItems.map((json) => new ItemData(json));
    return <ItemDisplayGrid items={items}></ItemDisplayGrid>;
}

const getTypedItems = (url: string) => {
    return getItems(url);
};

const getStatItems = (url: string) => {
    return getItems(url);
};

export class DisplayTab {
    url: string;
    getItems: () => Observable<JSX.Element>;
    constructor(
        url: string,
        getItems: (url: string) => Observable<JSX.Element>
    ) {
        this.url = url;
        this.getItems = () => getItems(url);
    }
}
export const AllDisplayTabs = {
    STAT_ITEM: new DisplayTab(
        'http://localhost:8081/ambrosia/get/stat_item/by_name?itemName=Eidolon',
        getStatItems
    ),
    UN_IDED: new DisplayTab(
        'http://localhost:8081/ambrosia/get/stat_item/by_name?itemName=Eidolon',
        getTypedItems
    ),
};
