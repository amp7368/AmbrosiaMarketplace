import { PrivateRouteInfo } from '../../../routes/PrivateRouteInfo';
import { RouteInfo } from '../../../routes/RouteInfo';
import { PageWrapper } from '../PageWrapper';
import { SideDrawer } from '../../common/side/SideDrawer';
import { SideDrawerState } from '../../common/side/SideDrawerState';
import { ProfileSideBar } from './ProfileSideBar';
import { ProfileMode } from './ProfileMode';
import { SideBarProps, MainPageProps } from '../PageWrapperProps';
import { ProfileMainPage } from './ProfileMainPage';

export class ProfilePage extends PageWrapper<ProfileMode> {
    createRoute(): RouteInfo {
        return new PrivateRouteInfo(this);
    }
    renderMainPage(props: MainPageProps<ProfileMode>): JSX.Element {
        return <ProfileMainPage {...props} />;
    }
    listTabs(): ProfileMode[] {
        return [ProfileMode.Preview, ProfileMode.Edit];
    }
    renderSideBar(props: SideBarProps<ProfileMode>): JSX.Element {
        return (
            <SideDrawer
                defaultState={SideDrawerState.OPEN}
                drawerStates={
                    new Map([[SideDrawerState.OPEN, <ProfileSideBar />]])
                }
                {...props}
            />
        );
    }
}
