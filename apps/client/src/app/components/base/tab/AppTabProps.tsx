import { ReactNode } from 'react';
import { Subject } from 'rxjs';

export interface AppTabsProps<Key> {
    defaultPage: Key;
    onTabSwitch: Subject<Key>;
    tabs: AppTabSingleProps<Key>[];
}
export interface AppTabSingleProps<Key> {
    tabKey: Key;
    tabElement: ReactNode;
    renderPage: () => JSX.Element;
}
