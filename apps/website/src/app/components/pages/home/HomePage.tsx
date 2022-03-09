import { RouteInfo } from '../../../routes/RouteInfo';
import { SideDrawer } from '../../common/side/SideDrawer';
import { PageWrapper } from '../PageWrapper';

export class HomePage extends PageWrapper {
    createRoute(): RouteInfo {
        return new RouteInfo(this);
    }

    renderMainPage(): JSX.Element {
        return <h1>Main Page</h1>;
    }
    renderSideBar(): JSX.Element {
        return <SideDrawer />;
    }
}
