import { Navigate } from 'react-router-dom';

import { RestrictedRouteInfo } from './RestrictedRouteInfo';
import { RouteProps } from './routeProps';

export class PrivateRouteInfo extends RestrictedRouteInfo {
    protected mapToElement(isLoggedIn: boolean) {
        if (isLoggedIn) {
            // if you're not logged in, log in
            return this.renderPage();
        } else {
            return <Navigate to={RouteProps.Auth.link} replace={true} />;
        }
    }
}
