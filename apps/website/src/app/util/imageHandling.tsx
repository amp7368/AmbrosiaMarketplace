import AmbrosiaLogo from '../../assets/menuBar/ambrosialogo.png';
import About from '../../assets/menuBar/buttons/iabout.png';
import Discord from '../../assets/menuBar/buttons/idiscord.png';
import SearchFilter from '../../assets/menuBar/buttons/ifilter.png';
import Market from '../../assets/menuBar/buttons/imarket.png';
import Money from '../../assets/menuBar/buttons/ipricehistory.png';
import Profile from '../../assets/menuBar/buttons/iprofile.png';
import Trade from '../../assets/menuBar/buttons/itrade.png';
import TopBackground from '../../assets/menuBar/menubarparquet.png';
import SidePanelChains from '../../assets/sideBar/sidebarchains.png';
import SidePanelBackground from '../../assets/sideBar/sidebarparquet.png';
import PageBackground from '../../assets/wallpaper.png';

class ImageHandler {
    private shouldResize = false;
    constructor(private src: any, private title: string) {}
    resize(shouldResize: boolean): ImageHandler {
        this.shouldResize = shouldResize;
        return this;
    }
    build() {
        let props: React.ImgHTMLAttributes<HTMLImageElement> = {
            src: this.src,
        };
        if (this.shouldResize)
            props = { ...props, width: '100%', height: '100%' };
        return <img {...props} alt={this.title}></img>;
    }
}
function convert(src: any, title: string) {
    return () => new ImageHandler(src, title);
}
export const AppImg = {
    Fallback: convert(AmbrosiaLogo, 'fallback'),
    Trade: convert(Trade, 'Trade'),
    Money: convert(Money, 'Money'),
    Profile: convert(Profile, 'Profile'),
    Discord: convert(Discord, 'Discord'),
    About: convert(About, 'About'),
    SearchFilter: convert(SearchFilter, 'Filter'),
    logo: convert(AmbrosiaLogo, 'Logo'),
    top: {
        background: {
            main: convert(TopBackground, ''),
        },
        button: {
            market: convert(Market, 'Market'),
        },
    },
    page: {
        background: {
            main: convert(PageBackground, ''),
        },
    },
    side: {
        background: {
            main: convert(SidePanelBackground, ''),
            chains: convert(SidePanelChains, ''),
        },
    },
};
