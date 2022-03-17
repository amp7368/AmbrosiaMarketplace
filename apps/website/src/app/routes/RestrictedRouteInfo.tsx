import { ObserveableToElement } from '@appleptr16/elemental';
import { Route } from 'react-router-dom';

import { selfUserQuery } from '../model/session/SelfUser.query';
import { IPageWrapper, RouteInfo } from './RouteInfo';

export abstract class RestrictedRouteInfo extends RouteInfo {
    protected abstract mapToElement(isLoggedIn: boolean): JSX.Element;
    constructor(props: IPageWrapper) {
        super(props);
        this.mapToElement = this.mapToElement.bind(this);
    }
    override renderRoute({ key }: { key: number }): JSX.Element {
        const element = ObserveableToElement({
            original: selfUserQuery.isLoggedIn,
            mappingFn: this.mapToElement,
        });

        return <Route key={key} path={this.props.link} element={element} />;
    }
}
