import styled from '@emotion/styled';
import { insidePadding } from 'elemental';
import { Observable } from 'rxjs';
import { ItemData } from '../../../model/item/ItemStore';
import { PageWrapper } from '../PageWrapper';
import { HomePageItemSection } from './HomePageItemSection';
const Root = styled(insidePadding(`10px`))`
    height: 100%;
`;
const empty: Observable<ItemData> = new Observable();
class HomePage extends PageWrapper {
    public renderMainPage = () => {
        return (
            <Root>
                <HomePageItemSection label="Promoted" itemProvider={empty} />
                <HomePageItemSection label="Most Viewed" itemProvider={empty} />
                <HomePageItemSection label="Newest" itemProvider={empty} />
            </Root>
        );
    };
}
export default HomePage;
