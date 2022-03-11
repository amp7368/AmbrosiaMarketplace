import { Button, Stack, styled } from '@mui/material';
import { ReactNode } from 'react';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import { SideDrawerState } from './SideDrawerState';

const StyledButton = styled(Button)(({ theme }) => ({
    backgroundImage: `radial-gradient(circle, ${theme.palette.primary.light}, transparent)`,
    transition: 'all 0.5s ease',
    color: theme.palette.text.primary,
}));
export const sideDrawerOpenButton: SideDrawerNavDefProps = {
    isOpening: true,
    element: <ChevronRightIcon fontSize="large" />,
};
export const sideDrawerCloseButton: SideDrawerNavDefProps = {
    isOpening: false,
    element: <ChevronLeftIcon fontSize="large" />,
};

type SideDrawerNavProps = SideDrawerNavDefProps & SideDrawerNavListProps;
const upperExtreme: [SideDrawerState, number] = [SideDrawerState.EXPANDED, 1];
const lowerExtreme: [SideDrawerState, number] = [SideDrawerState.CLOSED, -1];
function TopButton({ currentState, ...props }: SideDrawerNavProps) {
    const [extreme, goTowards] = props.isOpening ? upperExtreme : lowerExtreme;
    const isExtreme: boolean = extreme === currentState;
    let newState: SideDrawerState;
    if (isExtreme) newState = currentState;
    else newState = currentState + goTowards;
    return (
        <StyledButton onClick={() => props.setState(newState)}>
            {props.element}
        </StyledButton>
    );
}

export interface SideDrawerNavDefProps {
    isOpening: boolean;
    element: ReactNode;
}
export interface SideDrawerNavListProps {
    currentState: SideDrawerState;
    setState: (state: SideDrawerState) => void;
    buttons: SideDrawerNavDefProps[];
}
export function SideDrawerNavList(props: SideDrawerNavListProps) {
    const mappedButtons: ReactNode = props.buttons.map(
        (buttonProps: SideDrawerNavDefProps) => (
            <TopButton {...props} {...buttonProps} />
        )
    );
    return (
        <Stack justifyContent="space-between" direction="row">
            {mappedButtons}
        </Stack>
    );
}
