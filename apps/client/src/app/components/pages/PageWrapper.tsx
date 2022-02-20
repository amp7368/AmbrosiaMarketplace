import styled from '@emotion/styled';
import { ThemeProvider } from '@mui/material';
import {
    divWrapperWith,
    FlexColContainer,
    FlexElement,
    FlexRowContainer,
    PresetOverlap100Container,
    PresetOverlap100Element,
} from 'elemental';
import { PropsWithChildren } from 'react';

import { PageWrapperProps } from '../../routes/routeProps';
import { CSSConsts } from '../../util/CSSConsts';
import { ImgPageBackground } from '../../util/imageHandling';
import { SidePanel } from '../common/side/SidePanel';
import { StyledTopNavigation } from '../common/top/TopNav';
import { appTheme } from '../ThemeManager';

const StyledMainPage = styled('div')(
    (props) => `
    height: 100%;
    width: calc(100% - ${CSSConsts.SidePanel.width});
    overflow-y: scroll;
    ::-webkit-scrollbar {
        fill-opacity: 1;
    }
    ::-webkit-scrollbar-thumb {
        background-color: ${props.theme.palette.primary.main};
        background-clip: padding-box;
        border: 0.2em solid ${props.theme.palette.primary.dark};
    }
`
);

const Root = styled(PresetOverlap100Container)`
    --top-height: calc(9 / 16 / 6 * 100vw);
    height: 100vh;
    width: 100vw;
    margin: 0;
    padding: 0;
    overflow: hidden;
`;
const LeftRightSplitter = divWrapperWith(
    FlexElement,
    styled(FlexRowContainer)`
        height: calc(100vh - var(--top-height));
        width: 100%;
    `
);
function StyledBottom(props: PropsWithChildren<any>) {
    return (
        <LeftRightSplitter>
            <SidePanel />
            <StyledMainPage>{props.children}</StyledMainPage>
        </LeftRightSplitter>
    );
}
const Background = styled(PresetOverlap100Element)`
    z-index: -5;
`;
export abstract class PageWrapper {
    props: PageWrapperProps;
    constructor(props: PageWrapperProps) {
        this.props = props;
        this.renderElement = this.renderElement.bind(this);
        this.getName = this.getName.bind(this);
    }

    public renderElement: () => JSX.Element = () => {
        return (
            <ThemeProvider theme={appTheme}>
                <Root>
                    <Background>{ImgPageBackground}</Background>
                    <PresetOverlap100Element>
                        <FlexColContainer>
                            <StyledTopNavigation />
                            <StyledBottom>{this.renderMainPage()}</StyledBottom>
                        </FlexColContainer>
                    </PresetOverlap100Element>
                </Root>
            </ThemeProvider>
        );
    };

    public getName(): string {
        return this.props.title;
    }

    public abstract renderMainPage: () => JSX.Element;
}
