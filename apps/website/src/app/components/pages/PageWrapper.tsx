import { Box } from '@mui/material';
import { useState, useMemo, useRef } from 'react';

import { IPageWrapper, RouteInfo } from '../../routes/RouteInfo';
import { PageWrapperProps } from '../../routes/routeProps';
import { TopNavigation } from '../common/top/TopNavigation';
import { MainPageProps, SideBarProps } from './PageWrapperProps';

interface PageProps<Tab> {
    page: PageWrapper<Tab>;
}
interface PageState<Tab> {
    currentTab: Tab;
}
function Page<Tab>({ page }: PageProps<Tab>) {
    const tabs = useRef(page.listTabs());
    const [state, setState] = useState({
        currentTab: tabs.current[0],
    } as PageState<Tab>);

    const sideBarProps: SideBarProps<Tab> = {
        tabs: tabs.current,
        currentTab: state.currentTab,
        setTab: (tab: Tab) => {
            setState((state: PageState<Tab>) => ({
                ...state,
                currentTab: tab,
            }));
        },
    };
    return (
        <Box width="100vw" height="100vh" display="flex" flexDirection="column">
            <page.renderTopNav />
            <Box display="flex" flexGrow={1}>
                <page.renderSideBar {...sideBarProps} />
                <page.renderMainPage currentTab={state.currentTab} />
            </Box>
        </Box>
    );
}

export abstract class PageWrapper<Tab> implements IPageWrapper {
    constructor(public props: PageWrapperProps) {}
    abstract createRoute(): RouteInfo;

    abstract listTabs(): Tab[];

    PageElement(): JSX.Element {
        return <Page page={this} />;
    }
    renderTopNav(): JSX.Element {
        return <TopNavigation />;
    }
    abstract renderMainPage(props: MainPageProps<Tab>): JSX.Element;
    abstract renderSideBar(props: SideBarProps<Tab>): JSX.Element;
}
