import { Box, Button, Typography } from '@mui/material';
import { Subject } from 'rxjs';

import { AppTabSingleProps } from './AppTabProps';

export interface AppTabHeaderProps<Key> extends AppTabSingleProps<Key> {
    onTabSwitch: Subject<Key>;
}
export function AppTabHeader<Key>(props: AppTabHeaderProps<Key>) {
    return (
        <Button onClick={() => props.onTabSwitch.next(props.tabKey)}>
            <Box>
                <Typography>{props.tabElement}</Typography>
            </Box>
        </Button>
    );
}
