import { divWrapperWith, styledDivWrapper } from 'elemental';
import styled from '@emotion/styled';
interface ItemIconProps {
    icon: JSX.Element;
}
const ItemIcon = (props: ItemIconProps) => {
    return props.icon;
};
export const StyledItemIcon = divWrapperWith(
    styled(styledDivWrapper)``,
    ItemIcon
);
