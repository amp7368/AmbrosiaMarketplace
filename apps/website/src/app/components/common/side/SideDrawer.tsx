import { useTheme } from '@emotion/react';
import { StyledComponent } from '@emotion/styled';
import { ArrowLeft, ArrowRight } from '@mui/icons-material';
import {
    Box,
    BoxProps,
    Button,
    Container,
    Drawer,
    DrawerProps,
    Icon,
    Stack,
    Typography,
} from '@mui/material';
import { styled, Theme } from '@mui/material/styles';
import { ReactNode, useState } from 'react';

import { TopNavigation } from '../top/TopNavigation';
import {
    sideDrawerCloseButton as drawerCloseButton,
    SideDrawerNavDefProps,
    SideDrawerNavList,
    sideDrawerOpenButton as drawerOpenButton,
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
interface TabButtonProps<Tab> {
    isActive: boolean;
    tab: Tab;
    setTab: (tab: Tab) => void;
}
function TabButton<Tab>(props: TabButtonProps<Tab>) {
    const color = useTheme().palette.secondary;
    return (
        <Button
            variant="contained"
            sx={{
                fontWeight: 'bolder',
                backgroundColor: props.isActive ? color.light : color.dark,
                borderRadius: 0,
                boxShadow: 'none',
            }}
            onClick={() => props.setTab(props.tab)}
        >
            {props.isActive && <ArrowRight />}
            {props.tab}
            {props.isActive && <ArrowLeft />}
        </Button>
    );
}
export interface SideDrawerProps<Tab> {
    defaultState?: SideDrawerState;
    drawerStates: Map<SideDrawerState, ReactNode>;
    tabs: Tab[];
    currentTab: Tab;
    setTab: (tab: Tab) => void;
}
export function SideDrawer<Tab = null>({
    defaultState = SideDrawerState.OPEN,
    drawerStates,
    tabs = [],
    setTab,
    currentTab,
}: SideDrawerProps<Tab>) {
    const [state, setState] = useState(defaultState);

    const allStates: SideDrawerState[] = (
        Object.values(SideDrawerState) as SideDrawerState[]
    ).filter((st: SideDrawerState) => drawerStates.has(st));
    const indexOfState: number = allStates.indexOf(state);

    const sizeButtons: SideDrawerNavDefProps[] = [];
    const navButtons = tabs.map((tab: Tab) => (
        <TabButton isActive={tab === currentTab} setTab={setTab} tab={tab} />
    ));
    if (indexOfState > 0) sizeButtons.push(drawerCloseButton);
    if (indexOfState < allStates.length - 1) sizeButtons.push(drawerOpenButton);
    return (
        <StyledDrawer $currentState={state}>
            <SideDrawerNavList
                currentState={state}
                setState={setState}
                buttons={sizeButtons}
            />
            <Box padding="1rem .5rem 1rem .5rem" bgcolor="secondary.dark">
                <Stack direction="column" alignContent="center">
                    {navButtons}
                </Stack>
            </Box>
            {drawerStates.get(state)}
        </StyledDrawer>
    );
}
