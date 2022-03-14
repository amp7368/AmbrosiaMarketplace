export interface PageWrapperProps {
    pageType: PageId;
    title: string;
    link: string;
}

export enum PageId {
    Home = 'Home',
    Profile = 'Profile',
    Auth = 'Auth',
    Catalogue = 'Catalogue',
}
function createProps(pageType: PageId, title: string, link: string) {
    return {
        pageType: pageType,
        title: title,
        link: link,
    } as PageWrapperProps;
}
export const RouteProps = {
    Home: createProps(PageId.Home, 'Home', '/'),
    Profile: createProps(PageId.Profile, 'Profile', '/profile'),
    Auth: createProps(PageId.Auth, 'Login', '/login'),
};
