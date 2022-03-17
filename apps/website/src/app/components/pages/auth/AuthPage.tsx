import { PublicOnlyRouteInfo } from '../../../routes/PublicOnlyRouteInfo';
import { RouteInfo } from '../../../routes/RouteInfo';
import { SideDrawer } from '../../common/side/SideDrawer';
import { SideDrawerState } from '../../common/side/SideDrawerState';
import { PageWrapper } from '../PageWrapper';
import { MainPageProps, SideBarProps } from '../PageWrapperProps';
import { AuthSideDrawer } from './AuthSideBar';

export class AuthPage extends PageWrapper<undefined> {
    listTabs(): undefined[] {
        return [];
    }
    createRoute(): RouteInfo {
        return new PublicOnlyRouteInfo(this);
    }

    renderMainPage(props: MainPageProps<undefined>): JSX.Element {
        return <></>;
    }
    renderSideBar(props: SideBarProps<undefined>): JSX.Element {
        return (
            <SideDrawer
                defaultState={SideDrawerState.EXPANDED}
                drawerStates={
                    new Map([[SideDrawerState.EXPANDED, <AuthSideDrawer />]])
                }
                {...props}
            />
        );
    }
}
