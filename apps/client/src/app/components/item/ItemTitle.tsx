import { divWrapperWith, styledDivWrapper } from 'elemental';
import styled from '@emotion/styled';
interface ItemTitleProps {
    title: string;
}
const ItemTitle = (props: ItemTitleProps) => {
    return <h1>{props.title}</h1>;
};
export const StyledItemTitle: (props: ItemTitleProps) => JSX.Element =
    divWrapperWith(styled(styledDivWrapper)``, ItemTitle);
