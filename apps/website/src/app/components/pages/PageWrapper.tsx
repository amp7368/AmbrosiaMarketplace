import { PropsJustChildren } from '@appleptr16/elemental';
import { Box, Container, CssBaseline, Stack } from '@mui/material';

import { IPageWrapper, RouteInfo } from '../../routes/RouteInfo';
import { PageWrapperProps } from '../../routes/routeProps';
import { TopNavigation } from '../common/top/TopNavigation';

const StyledRootPage = (props: PropsJustChildren) => {
    return <Box sx={{ color: 'primary', padding: 2 }}>{props.children}</Box>;
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
            <Box display="flex" position="relative">
                {[<this.renderTopNav />, <this.renderSideBar />].map(
                    (el, i) => (
                        <Box key={i}>{el}</Box>
                    )
                )}
                <StyledRootPage>
                    <this.renderMainPage />
                </StyledRootPage>
            </Box>
        );
    }
    renderTopNav(): JSX.Element {
        return <TopNavigation hidden={false} />;
    }
    abstract renderMainPage(): JSX.Element;
    abstract renderSideBar(): JSX.Element;
}
