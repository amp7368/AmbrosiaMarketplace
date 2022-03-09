import {
    Box,
    Drawer,
    DrawerProps,
    SwipeableDrawer,
    SwipeableDrawerProps,
    Toolbar,
} from '@mui/material';
import { styled, Theme } from '@mui/material/styles';
import { ReactNode, useState } from 'react';

import { StyledStylingProps } from '../../../util/StyledStyling';
import { MiniDrawerState } from './MiniDrawerState';
import {
    miniDrawerCloseButton,
    MiniDrawerNavDefProps,
    MiniDrawerNavList,
    miniDrawerOpenButton,
} from './MiniDrawerNav';
import { StyledComponent } from '@emotion/styled';
import { TopNavigation } from '../../common/top/TopNavigation';

const commonMixin = (theme: Theme) => ({
    transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
    }),
    backgroundColor: theme.palette.primary.main,
    overflow: 'hidden',
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
    buttonTop: MiniDrawerNavDefProps[];
    contents: (props: MiniDrawerProps) => ReactNode;
}

const statedMixin: Record<MiniDrawerState, StatedConsts> = {
    [MiniDrawerState.CLOSED]: {
        styling: createMixinStyling({ width: '4rem' }),
        buttonTop: [miniDrawerOpenButton],
        contents: (props: MiniDrawerProps) => props.closedState,
    },
    [MiniDrawerState.OPEN]: {
        styling: createMixinStyling({ width: '16rem' }),
        buttonTop: [miniDrawerCloseButton, miniDrawerOpenButton],
        contents: (props: MiniDrawerProps) => props.openState,
    },
    [MiniDrawerState.EXPANDED]: {
        styling: createMixinStyling({ width: '48rem' }),
        buttonTop: [miniDrawerCloseButton],
        contents: (props: MiniDrawerProps) => props.expandedState,
    },
};
interface StyledDrawerProps {
    $currentState: MiniDrawerState;
}
const StyledDrawer: StyledComponent<DrawerProps, StyledDrawerProps> = styled(
    Drawer
)<StyledDrawerProps>(({ theme, $currentState: state }) => {
    const styling = {
        zIndex: 0,
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

export interface MiniDrawerProps {
    closedState?: ReactNode;
    openState?: ReactNode;
    expandedState?: ReactNode;
}
export function MiniDrawer(props: MiniDrawerProps) {
    const [state, setState] = useState(MiniDrawerState.OPEN);

    const statedConsts: StatedConsts = statedMixin[state];
    return (
        <StyledDrawer variant="permanent" $currentState={state}>
            <TopNavigation hidden={true} />
            <MiniDrawerNavList
                currentState={state}
                setState={setState}
                buttons={statedConsts.buttonTop}
            />
            {[5, 56, 5, 5, 5, 5, 5, 5, 5, 5].map(() => (
                <MiniDrawerNavList
                    currentState={state}
                    setState={setState}
                    buttons={statedConsts.buttonTop}
                />
            ))}
            {statedConsts.contents(props)}
        </StyledDrawer>
    );
}
