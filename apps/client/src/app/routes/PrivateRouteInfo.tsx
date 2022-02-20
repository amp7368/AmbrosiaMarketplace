import { Navigate } from 'react-router-dom';
import { RestrictedRouteInfo } from './RestrictedRouteInfo';

export class PrivateRouteInfo extends RestrictedRouteInfo {
    protected mapToElement(isLoggedIn: boolean) {
        if (isLoggedIn) {
            // if you're not logged in, log in
            return this.page.renderElement();
        } else {
            return <Navigate to={'/auth'} replace={true} />;
        }
    }
}
