import { StyledComponent } from '@emotion/styled';
import { Box, BoxProps, Drawer, DrawerProps } from '@mui/material';
import { styled, Theme } from '@mui/material/styles';
import { ReactNode, useState } from 'react';

import { TopNavigation } from '../top/TopNavigation';
import {
    sideDrawerCloseButton,
    SideDrawerNavDefProps,
    SideDrawerNavList,
    sideDrawerOpenButton,
} from './SideDrawerNav';
import { SideDrawerState } from './SideDrawerState';

const commonMixin = (theme: Theme) => ({
    transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
    }),
    backgroundColor: theme.palette.primary.dark,
    border: 0,
    overflow: 'hidden',
    height: '100%',
});
interface CreateMixinStylingProps {
    width: string;
}
function createMixinStyling(
    props: CreateMixinStylingProps
): (theme: Theme) => any {
    return () => ({
        width: props.width,
    });
}
interface StatedConsts {
    styling: (theme: Theme) => any;
}

const statedMixin: Record<SideDrawerState, StatedConsts> = {
    [SideDrawerState.CLOSED]: {
        styling: createMixinStyling({ width: '4rem' }),
    },
    [SideDrawerState.OPEN]: {
        styling: createMixinStyling({ width: '16rem' }),
    },
    [SideDrawerState.MORE_OPEN]: {
        styling: createMixinStyling({ width: '32rem' }),
    },
    [SideDrawerState.EXPANDED]: {
        styling: createMixinStyling({ width: '48rem' }),
    },
};
interface StyledDrawerProps {
    $currentState: SideDrawerState;
}
const StyledDrawer: StyledComponent<BoxProps, StyledDrawerProps> = styled(
    Box
)<StyledDrawerProps>(({ theme, $currentState: state }) => {
    const styling = {
        ...commonMixin(theme),
        ...statedMixin[state].styling(theme),
    };
    return {
        ...styling,
        '& .MuiDrawer-paper': {
            ...styling,
        },
    };
});

export interface SideDrawerProps {
    defaultState?: SideDrawerState;
    drawerStates: Map<SideDrawerState, ReactNode>;
}
export function SideDrawer({
    defaultState = SideDrawerState.OPEN,
    drawerStates,
}: SideDrawerProps) {
    const [state, setState] = useState(defaultState);

    const allStates: SideDrawerState[] = (
        Object.values(SideDrawerState) as SideDrawerState[]
    ).filter((st: SideDrawerState) => drawerStates.has(st));
    const indexOfState: number = allStates.indexOf(state);

    const buttons: SideDrawerNavDefProps[] = [];
    if (indexOfState > 0) buttons.push(sideDrawerCloseButton);
    if (indexOfState < allStates.length - 1) buttons.push(sideDrawerOpenButton);
    return (
        <StyledDrawer $currentState={state}>
            <SideDrawerNavList
                currentState={state}
                setState={setState}
                buttons={buttons}
            />
            {drawerStates.get(state)}
        </StyledDrawer>
    );
}
