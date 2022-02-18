import * as React from 'react';
import { Navigate, Route } from 'react-router-dom';
import { sessionQuery } from '../../model/user/session/Session.query';
import { observeableToElement } from 'elemental';
import CataloguePage from './catalogue/CataloguePage';
import HomePage from './home/HomePage';
import LoginPage from './login/LoginPage';
import ProfilePage from './profile/ProfilePage';
import { AllRouteProps, PageWrapperProps } from './routeProps';
import SignupPage from './signup/SignupPage';

interface IPageWrapper {
    renderElement(): JSX.Element;
}
export interface ToRouteProps {
    key: number;
}
export class RouteInfo {
    props: PageWrapperProps;
    page: IPageWrapper;
    constructor(props: PageWrapperProps, page: IPageWrapper) {
        this.props = props;
        this.page = page;
        this.toRoute = this.toRoute.bind(this);
    }
    public getName() {
        return this.props.title;
    }
    public toRoute(toRouteProps: ToRouteProps): JSX.Element {
        return (
            <Route
                key={toRouteProps.key}
                path={this.props.link}
                element={this.page.renderElement()}
            />
        );
    }
}
abstract class RestrictedRouteInfo extends RouteInfo {
    protected abstract mapToElement(isLoggedIn: boolean): JSX.Element;
    constructor(props: PageWrapperProps, page: IPageWrapper) {
        super(props, page);
        this.mapToElement = this.mapToElement.bind(this);
    }
    public override toRoute(toRouteProps: ToRouteProps): JSX.Element {
        const element = observeableToElement({
            original: sessionQuery.isLoggedIn,
            mappingFn: this.mapToElement,
        });
        return (
            <Route
                key={toRouteProps.key}
                path={this.props.link}
                element={element}
            />
        );
    }
}
class PrivateRouteInfo extends RestrictedRouteInfo {
    protected mapToElement(isLoggedIn: boolean) {
        if (isLoggedIn) {
            // if you're not logged in, log in
            return this.page.renderElement();
        } else {
            return <Navigate to={'/login'} replace={true} />;
        }
    }
}
class PublicOnlyRouteInfo extends RestrictedRouteInfo {
    protected mapToElement(isLoggedIn: boolean) {
        if (isLoggedIn) {
            // if you're logged in, don't log in again
            return <Navigate to={'/profile'} replace={true} />;
        } else {
            return this.page.renderElement();
        }
    }
}
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
    LoginRoute: new PublicOnlyRouteInfo(
        AllRouteProps.LoginProps,
        new LoginPage(AllRouteProps.LoginProps)
    ),
    SignupRoute: new PublicOnlyRouteInfo(
        AllRouteProps.SignupProps,
        new SignupPage(AllRouteProps.SignupProps)
    ),
};
export const AllRoutesList: RouteInfo[] = [
    AllRoutes.HomeRoute,
    AllRoutes.CatalogueRoute,
    AllRoutes.ProfileRoute,
    AllRoutes.LoginRoute,
    AllRoutes.SignupRoute,
];
