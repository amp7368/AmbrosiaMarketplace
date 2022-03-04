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
