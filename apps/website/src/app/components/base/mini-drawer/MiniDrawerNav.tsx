import { Button, Stack } from '@mui/material';
import { ReactNode } from 'react';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import { MiniDrawerState } from './MiniDrawerState';

export const miniDrawerOpenButton: MiniDrawerNavDefProps = {
    isOpening: true,
    element: <ChevronRightIcon />,
};
export const miniDrawerCloseButton: MiniDrawerNavDefProps = {
    isOpening: false,
    element: <ChevronLeftIcon />,
};

type MiniDrawerNavProps = MiniDrawerNavDefProps & MiniDrawerNavListProps;
const upperExtreme: [MiniDrawerState, number] = [MiniDrawerState.EXPANDED, 1];
const lowerExtreme: [MiniDrawerState, number] = [MiniDrawerState.CLOSED, -1];
function TopButton({ currentState, ...props }: MiniDrawerNavProps) {
    const [extreme, goTowards] = props.isOpening ? upperExtreme : lowerExtreme;
    const isExtreme: boolean = extreme === currentState;
    let newState: MiniDrawerState;
    if (isExtreme) newState = currentState;
    else newState = currentState + goTowards;
    return (
        <Button onClick={() => props.setState(newState)}>
            {props.element}
        </Button>
    );
}

export interface MiniDrawerNavDefProps {
    isOpening: boolean;
    element: ReactNode;
}
export interface MiniDrawerNavListProps {
    currentState: MiniDrawerState;
    setState: (state: MiniDrawerState) => void;
    buttons: MiniDrawerNavDefProps[];
}
export function MiniDrawerNavList(props: MiniDrawerNavListProps) {
    const mappedButtons: ReactNode = props.buttons.map(
        (buttonProps: MiniDrawerNavDefProps) => (
            <TopButton {...props} {...buttonProps} />
        )
    );
    return (
        <Stack bgcolor="yellow" justifyContent="space-between" direction="row">
            {mappedButtons}
        </Stack>
    );
}
