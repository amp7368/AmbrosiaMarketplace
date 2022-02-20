import { Route } from 'react-router-dom';
import { PageWrapperProps } from './routeProps';

export interface IPageWrapper {
    renderElement(): JSX.Element;
}

export interface ToRouteProps {
    key: number;
}

export class RouteInfo {
    props: PageWrapperProps;
    page: IPageWrapper;
    constructor(props: PageWrapperProps, page: IPageWrapper) {
        this.props = props;
        this.page = page;
        this.toRoute = this.toRoute.bind(this);
    }
    public getName() {
        return this.props.title;
    }
    public toRoute(toRouteProps: ToRouteProps): JSX.Element {
        return (
            <Route
                key={toRouteProps.key}
                path={this.props.link}
                element={this.page.renderElement()}
            />
        );
    }
}
