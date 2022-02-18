import styled from '@emotion/styled';
import AmbrosiaLogo from '../../assets/menuBar/ambrosialogo.png';
import TopBackground from '../../assets/menuBar/menubarparquet.png';
import Market from '../../assets/menuBar/buttons/imarket.png';
import Trade from '../../assets/menuBar/buttons/itrade.png';
import Money from '../../assets/menuBar/buttons/ipricehistory.png';
import Profile from '../../assets/menuBar/buttons/iprofile.png';
import Discord from '../../assets/menuBar/buttons/idiscord.png';
import About from '../../assets/menuBar/buttons/iabout.png';
import SearchFilter from '../../assets/menuBar/buttons/ifilter.png';
import SidePanelBackground from '../../assets/sideBar/sidebarparquet.png';
import SidePanelChains from '../../assets/sideBar/sidebarchains.png';
import PageBackground from '../../assets/wallpaper.png';

const Img = styled.img`
    height: 100%;
    width: 100%;
`;
function convert(src: any, alt: string) {
    return <Img src={src} alt={alt} />;
}
export const ImgFallback = convert(AmbrosiaLogo, 'fallback');
export const ImgAmbrosiaLogo = convert(AmbrosiaLogo, 'Logo');
export const ImgTopBackground = convert(TopBackground, '');
export const ImgMarket = convert(Market, 'Market');
export const ImgTrade = convert(Trade, 'Trade');
export const ImgMoney = convert(Money, 'Money');
export const ImgProfile = convert(Profile, 'Profile');
export const ImgDiscord = convert(Discord, 'Discord');
export const ImgAbout = convert(About, 'About');
export const ImgSearchFilter = convert(SearchFilter, 'Filter');
export const ImgSidePanelBackground = convert(SidePanelBackground, '');
export const ImgSidePanelChains = convert(SidePanelChains, '');
export const ImgPageBackground = convert(PageBackground, '');
