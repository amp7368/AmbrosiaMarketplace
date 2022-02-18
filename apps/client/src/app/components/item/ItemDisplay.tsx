import styled from '@emotion/styled';
import { styledDivWrapper } from 'elemental';
import { ItemData } from '../../model/item/ItemStore';
import { ImgFallback } from '../../util/imageHandling';
import { StyledItemIcon } from './ItemIcon';
import { StyledItemTitle } from './ItemTitle';
interface ItemDisplayProps {
    item: ItemData;
}
const StyledItemDisplayContainer = styled(styledDivWrapper)``;
export const ItemDisplay = (props: ItemDisplayProps) => {
    return (
        <StyledItemDisplayContainer>
            <StyledItemTitle title={props.item.meta.name.str}></StyledItemTitle>
            <StyledItemIcon icon={ImgFallback}></StyledItemIcon>
        </StyledItemDisplayContainer>
    );
};
