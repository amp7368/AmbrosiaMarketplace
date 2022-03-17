export interface MainPageProps<Tab> {
    currentTab: Tab;
}
export interface SideBarProps<Tab> {
    currentTab: Tab;
    tabs: Tab[];
    setTab: (tab: Tab) => void;
}
