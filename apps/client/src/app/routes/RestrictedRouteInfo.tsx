import { ObserveableToElement } from 'elemental';
import { Route } from 'react-router-dom';
import { sessionQuery } from '../model/user/session/Session.query';
import { IPageWrapper, RouteInfo, ToRouteProps } from './RouteInfo';
import { PageWrapperProps } from './routeProps';

export abstract class RestrictedRouteInfo extends RouteInfo {
    protected abstract mapToElement(isLoggedIn: boolean): JSX.Element;
    constructor(props: PageWrapperProps, page: IPageWrapper) {
        super(props, page);
        this.mapToElement = this.mapToElement.bind(this);
    }
    public override toRoute(toRouteProps: ToRouteProps): JSX.Element {
        const element = ObserveableToElement({
            original: sessionQuery.isLoggedIn,
            mappingFn: this.mapToElement,
        });
        return (
            <Route
                key={toRouteProps.key}
                path={this.props.link}
                element={element}
            />
        );
    }
}
