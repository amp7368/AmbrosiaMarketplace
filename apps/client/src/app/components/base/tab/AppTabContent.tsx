import { ObserveableToElement } from 'elemental';

import { AppTabsProps } from './AppTabProps';
import { useMemo } from 'react';
import { startWith, Observable } from 'rxjs';

export function AppTabContent<Key>(props: AppTabsProps<Key>): JSX.Element {
    function mappingFn(desiredTab: Key) {
        if (!desiredTab) desiredTab = props.defaultPage;
        for (const propTab of props.tabs) {
            if (propTab.tabKey === desiredTab) {
                return propTab.renderPage();
            }
        }
        // shouldn't happen
        console.error("The tab specified doesn't exist");
        return null;
    }

    const onTabSwitch: Observable<Key> = useMemo(
        () => props.onTabSwitch.pipe(startWith(props.defaultPage)),
        [props.onTabSwitch, props.defaultPage]
    );
    return (
        <ObserveableToElement original={onTabSwitch} mappingFn={mappingFn} />
    );
}
