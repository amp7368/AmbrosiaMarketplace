import AuthPage from '../components/pages/auth/AuthPage';
import CataloguePage from '../components/pages/catalogue/CataloguePage';
import HomePage from '../components/pages/home/HomePage';
import ProfilePage from '../components/pages/profile/ProfilePage';
import { PrivateRouteInfo } from './PrivateRouteInfo';
import { PublicOnlyRouteInfo } from './PublicOnlyRouteInfo';
import { RouteInfo } from './RouteInfo';
import { AllRouteProps } from './routeProps';

export const AllRoutes = {
    HomeRoute: new RouteInfo(
        AllRouteProps.HomeProps,
        new HomePage(AllRouteProps.HomeProps)
    ),
    CatalogueRoute: new RouteInfo(
        AllRouteProps.CatalogueProps,
        new CataloguePage(AllRouteProps.CatalogueProps)
    ),
    ProfileRoute: new PrivateRouteInfo(
        AllRouteProps.ProfileProps,
        new ProfilePage(AllRouteProps.ProfileProps)
    ),
    AuthRoute: new PublicOnlyRouteInfo(
        AllRouteProps.AuthProps,
        new AuthPage(AllRouteProps.AuthProps)
    ),
};
export const AllRoutesList: RouteInfo[] = [
    AllRoutes.HomeRoute,
    AllRoutes.CatalogueRoute,
    AllRoutes.ProfileRoute,
    AllRoutes.AuthRoute,
];
