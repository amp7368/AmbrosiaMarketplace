import { HomePage } from '../components/pages/home/HomePage';
import { ProfilePage } from '../components/pages/profile/ProfilePage';
import { RouteInfo } from './RouteInfo';
import { PageId, PageWrapperProps } from './routeProps';

function createProps(
    pageType: PageId,
    title: string,
    link: string
): PageWrapperProps {
    return {
        pageType: pageType,
        title: title,
        link: link,
    } as PageWrapperProps;
}
export const AllRoutes = {
    HomeRoute: new HomePage(
        createProps(PageId.Home, 'Home', '/')
    ).createRoute(),
    // DesignRoute: new DesignPage(
    //     createProps(PageId.Design, 'Design', '/design')
    // ).createRoute(),
    ProfileRoute: new ProfilePage(
        createProps(PageId.Profile, 'Profile', '/profile')
    ).createRoute(),
};

const AllRouteProps = {
    AuthProps: createProps(PageId.Auth, 'Authentication', '/auth'),
};
