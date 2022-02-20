import {
    divWrapperWith,
    FlexColContainer,
    FlexRowContainer,
    insidePadding,
} from 'elemental';
import styled from '@emotion/styled';
import {
    ImgAbout,
    ImgDiscord,
    ImgFallback,
    ImgSearchFilter,
} from '../../../util/imageHandling';

const StyledTopButton = styled.div`
    height: 75%;
`;
function createButton(img: JSX.Element, label: string, link: string) {
    return <StyledTopButton key={link}>{img}</StyledTopButton>;
}
const NavSearchTopButtons = [
    createButton(ImgDiscord, 'Discord', '/discord'),
    createButton(ImgAbout, 'About', '/about'),
    createButton(ImgFallback, 'Account', '/login'),
];
const NavSearchTop = () => {
    return NavSearchTopButtons;
};
const StyledNavSearchTop = splitVertically(NavSearchTop, `5px`);
function splitVertically(element: () => React.ReactNode, padding: string) {
    return divWrapperWith(
        styled.div`
            height: 50%;
        `,
        styled(insidePadding(padding))`
            width: 100%;
        `,
        styled(FlexRowContainer)`
            justify-content: space-evenly;
        `,
        element
    );
}
const SearchInput = styled.input(
    ({ theme }) => `
    background-color: ${theme.palette.primary.light};
    color: ${theme.palette.primary.contrastText};
    text-shadow: none;
    text-indent: 15px;
    font-size: 1.5em;
    font-weight: bolder;
    ::placeholder {
        color: ${theme.palette.primary.contrastText};
    }
    outline-style: none;
    border-style: none;
    border-radius: 10px;
    width: 80%;
`
);
const SearchFilterImg = styled.div(() => ``);
const NavSearchBottom = () => {
    return (
        <>
            <SearchFilterImg>{ImgSearchFilter}</SearchFilterImg>
            <SearchInput placeholder="Search Mythic..." />
        </>
    );
};
const StyledNavSearchBottom = splitVertically(NavSearchBottom, `10px`);
const NavSearch = () => {
    return (
        <>
            <StyledNavSearchTop />
            <StyledNavSearchBottom />
        </>
    );
};
export const StyledNavSearch = divWrapperWith(
    styled(FlexColContainer)``,
    NavSearch
);
