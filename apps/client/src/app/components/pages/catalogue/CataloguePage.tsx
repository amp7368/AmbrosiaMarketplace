import { PageWrapper } from '../PageWrapper';
import { AppTabSwitcher } from '../../base/tab/AppTabSwitcher';
import { AppTabsProps } from '../../base/tab/AppTabProps';
import { Subject } from 'rxjs';
import { CataloguePageIdentified } from './CataloguePageIdentified';
import { CataloguePageNonIdentified } from './CataloguePageNonIdentified';
import { AppTabContent } from '../../base/tab/AppTabContent';
enum CatalogueTab {
    IDENTIFIED_ITEM,
    NONIDENTIFIED_ITEM,
}
const tabs: AppTabsProps<CatalogueTab> = {
    defaultPage: CatalogueTab.NONIDENTIFIED_ITEM,
    onTabSwitch: new Subject<CatalogueTab>(),
    tabs: [
        {
            tabKey: CatalogueTab.NONIDENTIFIED_ITEM,
            tabElement: 'Base Items',
            renderPage: () => <CataloguePageNonIdentified />,
        },
        {
            tabKey: CatalogueTab.IDENTIFIED_ITEM,
            tabElement: 'Stat Items',
            renderPage: () => <CataloguePageIdentified />,
        },
    ],
};
export default class CataloguePage extends PageWrapper {
    public renderMainPage: () => JSX.Element = () => {
        return (
            <>
                <AppTabSwitcher {...tabs} />
                <AppTabContent {...tabs} />
            </>
        );
    };
}
