import { Navigate } from 'react-router-dom';
import { RestrictedRouteInfo } from './RestrictedRouteInfo';
import { RouteProps } from './routeProps';

export class PublicOnlyRouteInfo extends RestrictedRouteInfo {
    protected mapToElement(isLoggedIn: boolean) {
        if (isLoggedIn) {
            // if you're logged in, don't log in again
            return <Navigate to={RouteProps.Profile.link} replace={true} />;
        } else {
            return this.renderPage();
        }
    }
}
