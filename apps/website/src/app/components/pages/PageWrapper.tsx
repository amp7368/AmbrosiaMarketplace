import { PropsJustChildren } from '@appleptr16/elemental';
import { Box, Container, CssBaseline, Stack, Typography } from '@mui/material';

import { IPageWrapper, RouteInfo } from '../../routes/RouteInfo';
import { PageWrapperProps } from '../../routes/routeProps';
import { TopNavigation } from '../common/top/TopNavigation';

const StyledRootPage = (props: PropsJustChildren) => {
    return (
        <Box display="flex" flexGrow={1}>
            {props.children}
        </Box>
    );
};
export abstract class PageWrapper implements IPageWrapper {
    props: PageWrapperProps;
    constructor(props: PageWrapperProps) {
        this.props = props;
    }

    public getName(): string {
        return this.props.title;
    }

    abstract createRoute(): RouteInfo;

    PageElement(): JSX.Element {
        return (
            <Box
                width="100vw"
                height="100vh"
                display="flex"
                flexDirection="column"
            >
                <this.renderTopNav />
                <Box display="flex" flexGrow={1}>
                    <this.renderSideBar />
                    <this.renderMainPage />
                </Box>
            </Box>
        );
    }
    renderTopNav(): JSX.Element {
        return <TopNavigation />;
    }
    abstract renderMainPage(): JSX.Element;
    abstract renderSideBar(): JSX.Element;
}
