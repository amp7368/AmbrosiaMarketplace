import styled from '@emotion/styled';
import { StyledDiv100 } from 'elemental';
import { ItemData } from '../../model/item/ItemStore';
import { CreateElement } from '../../util/PropUtils';
import { ItemDisplay } from './ItemDisplay';
interface ItemDisplayGridProps {
    items: ItemData[];
}
const ItemDisplayGridContainer = styled(StyledDiv100)`
    display: grid;
`;
export const ItemDisplayGrid: CreateElement<ItemDisplayGridProps> = (
    props: ItemDisplayGridProps
) => {
    const items = props.items.map((item: ItemData) => {
        return <ItemDisplay item={item}></ItemDisplay>;
    });
    return <ItemDisplayGridContainer>{items}</ItemDisplayGridContainer>;
};
