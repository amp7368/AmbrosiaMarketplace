import { AuthPage } from '../components/pages/auth/AuthPage';
import { HomePage } from '../components/pages/home/HomePage';
import { ProfilePage } from '../components/pages/profile/ProfilePage';
import { PageId, PageWrapperProps, RouteProps } from './routeProps';

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
    Home: new HomePage(RouteProps.Home).createRoute(),
    Profile: new ProfilePage(RouteProps.Profile).createRoute(),
    Auth: new AuthPage(RouteProps.Auth).createRoute(),
};
