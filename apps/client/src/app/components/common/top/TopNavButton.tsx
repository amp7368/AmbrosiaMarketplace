import { divWrapperWith, FlexElement } from 'elemental';
import styled from '@emotion/styled';

export interface NavButtonProps {
    icon: JSX.Element;
    label: string;
    link: string;
}
const TopNavButton = (props: NavButtonProps) => {
    return <a href={props.link}>{props.icon}</a>;
};
export const StyledTopNavButton = divWrapperWith(
    styled(FlexElement)`
        height: 50%;
        align-self: center;
    `,
    TopNavButton
);
