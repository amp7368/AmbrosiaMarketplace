import { divWrapperWith, FlexElement } from 'elemental';
import styled from '@emotion/styled';
import {
    ImgTopBackground,
    ImgTrade,
    ImgMoney,
    ImgMarket,
    ImgProfile,
} from '../../../util/imageHandling';
import { NavButtonProps, StyledTopNavButton } from './TopNavButton';
import { StyledTopNavHomeButton } from './TopNavHomeButton';
import { StyledNavSearch } from './TopNavSearch';
import { FlexRowContainer } from 'elemental';
import { PresetOverlap100Container, PresetOverlap100Element } from 'elemental';

function makeNavButtonProps(
    icon: JSX.Element,
    label: string,
    link: string
): NavButtonProps {
    return { icon: icon, label: label, link: link };
}
const navButtons: NavButtonProps[] = [
    makeNavButtonProps(ImgMarket, 'Catalogue', '/catalogue'),
    makeNavButtonProps(ImgTrade, 'Trade', '/trade'),
    makeNavButtonProps(ImgProfile, 'Profile', '/profile'),
    makeNavButtonProps(ImgMoney, 'Search', '/search'),
];

const TopNavigationContentContainer = styled(FlexRowContainer)`
    justify-content: space-between;
`;
const StyledNavButtonsWrapper = styled(FlexRowContainer)`
    width: 40%;
    justify-content: space-evenly;
`;
const StyledNavSearchWrapper = styled.div`
    width: 40%;
`;
const StyledTopNavHomeButtonContainer = styled.div``;
const TopNavigation = () => {
    const navButtonElements = navButtons.map((props, i) => {
        return <StyledTopNavButton {...props} key={i}></StyledTopNavButton>;
    });
    return (
        <>
            <PresetOverlap100Element>
                {ImgTopBackground}
            </PresetOverlap100Element>
            <PresetOverlap100Element>
                <TopNavigationContentContainer>
                    <StyledTopNavHomeButtonContainer>
                        <StyledTopNavHomeButton />
                    </StyledTopNavHomeButtonContainer>
                    <StyledNavButtonsWrapper>
                        {navButtonElements}
                    </StyledNavButtonsWrapper>
                    <StyledNavSearchWrapper>
                        <StyledNavSearch />
                    </StyledNavSearchWrapper>
                </TopNavigationContentContainer>
            </PresetOverlap100Element>
        </>
    );
};

export const StyledTopNavigation = divWrapperWith(
    FlexElement,
    styled(PresetOverlap100Container)`
        height: var(--top-height);
        width: 100vw;
    `,
    TopNavigation
);
