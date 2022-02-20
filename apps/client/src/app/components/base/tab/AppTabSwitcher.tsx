import { Box } from '@mui/material';
import { PropsWithChildren } from 'react';

import { AppTabHeader } from './AppTabHeader';
import { AppTabSingleProps, AppTabsProps } from './AppTabProps';

function StyledTabContainer(props: PropsWithChildren<unknown>) {
    return <Box sx={{ width: '100%' }}>{props.children}</Box>;
}

export function AppTabSwitcher<Key extends React.Key>(
    props: AppTabsProps<Key>
) {
    const tabElements = props.tabs.map((tab: AppTabSingleProps<Key>) => (
        <AppTabHeader
            {...tab}
            onTabSwitch={props.onTabSwitch}
            key={tab.tabKey}
        />
    ));
    return <StyledTabContainer>{tabElements}</StyledTabContainer>;
}
