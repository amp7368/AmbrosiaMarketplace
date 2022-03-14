import { PublicOnlyRouteInfo } from '../../../routes/PublicOnlyRouteInfo';
import { RouteInfo } from '../../../routes/RouteInfo';
import { SideDrawer } from '../../common/side/SideDrawer';
import { SideDrawerState } from '../../common/side/SideDrawerState';
import { PageWrapper } from '../PageWrapper';
import { AuthSideDrawer } from './AuthSideBar';

export class AuthPage extends PageWrapper {
    createRoute(): RouteInfo {
        return new PublicOnlyRouteInfo(this);
    }

    renderMainPage(): JSX.Element {
        return <></>;
    }
    renderSideBar(): JSX.Element {
        return (
            <SideDrawer
                defaultState={SideDrawerState.EXPANDED}
                drawerStates={
                    new Map([[SideDrawerState.EXPANDED, <AuthSideDrawer />]])
                }
            />
        );
    }
}
