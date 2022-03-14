import { PrivateRouteInfo } from '../../../routes/PrivateRouteInfo';
import { RouteInfo } from '../../../routes/RouteInfo';
import { PageWrapper } from '../PageWrapper';

export class ProfilePage extends PageWrapper {
    createRoute(): RouteInfo {
        return new PrivateRouteInfo(this);
    }
    renderMainPage(): JSX.Element {
        return <h1>Profile Page</h1>;
    }
    renderSideBar(): JSX.Element {
        return <h2>side</h2>;
    }
}
