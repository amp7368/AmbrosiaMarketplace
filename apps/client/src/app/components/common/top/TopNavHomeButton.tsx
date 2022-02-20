import { divWrapperWith } from 'elemental';
import styled from '@emotion/styled';
import { ImgAmbrosiaLogo } from '../../../util/imageHandling';

const StyledLink = styled.a`
    height: 125%;
    display: inline-block;
    z-index: 1;
    border-radius: 100%;
    width: fit-content;
`;
const TopNavHomeButton = () => {
    return <StyledLink href="/">{ImgAmbrosiaLogo}</StyledLink>;
};
export const StyledTopNavHomeButton = divWrapperWith(TopNavHomeButton);
