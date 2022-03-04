import { PropsJustChildren } from '@appleptr16/elemental';
import { Box, Container, ThemeProvider } from '@mui/material';
import { ReactNode } from 'react';

import { IPageWrapper, RouteInfo } from '../../routes/RouteInfo';
import { PageWrapperProps } from '../../routes/routeProps';
import { TopNavigation } from '../common/nav/top/TopNavigation';
import { appTheme } from '../ThemeManager';

const StyledRoot = (props: PropsJustChildren) => (
    <Box color={'primary'}>{props.children}</Box>
);
const StyledContentRoot = (props: PropsJustChildren) => {
    return <Container sx={{ color: 'primary' }}>{props.children}</Container>;
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
            <ThemeProvider theme={appTheme}>
                <StyledRoot>
                    <TopNavigation />
                    <StyledContentRoot>
                        {this.renderMainPage()}
                    </StyledContentRoot>
                </StyledRoot>
            </ThemeProvider>
        );
    }
    abstract renderMainPage(): ReactNode;
}
