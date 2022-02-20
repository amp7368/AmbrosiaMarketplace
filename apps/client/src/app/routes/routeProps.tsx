export interface PageWrapperProps {
    pageType: PageType;
    title: string;
    link: string;
}

export enum PageType {
    Catalogue,
    Home,
    Profile,
    Auth,
}


function createProps(pageType: PageType, title: string, link: string) {
    return {
        pageType: pageType,
        title: title,
        link: link,
    } as PageWrapperProps;
}
export const AllRouteProps = {
    CatalogueProps: createProps(PageType.Catalogue, 'Catalogue', '/catalogue'),
    HomeProps: createProps(PageType.Home, 'Home', '/'),
    ProfileProps: createProps(PageType.Profile, 'Profile', '/profile'),
    AuthProps: createProps(PageType.Auth, 'Authentication', '/auth'),
};
