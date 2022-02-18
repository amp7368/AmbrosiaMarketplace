import { Observable } from 'rxjs';
import styled from '@emotion/styled';
import { ItemData } from '../../../model/item/ItemStore';
import { divWrapperWith, insidePadding } from 'elemental';
interface ItemSectionProps {
    itemProvider: Observable<ItemData>;
    label: string;
}
const Root = divWrapperWith(
    styled.div`
        width: 100%;
        height: ${300 / 5}%;
    `,
    insidePadding(`5px`)
);
const StyledLabelText = styled.p(
    ({ theme }) => `
    color: ${theme.palette.primary.contrastText};
    font-size: 1.5em;
    font-weight: 200;
`
);
const SectionLabel = (props: { label: string }) => {
    return <StyledLabelText>{props.label}</StyledLabelText>;
};
export const HomePageItemSection = (props: ItemSectionProps) => {
    return (
        <Root>
            <SectionLabel label={props.label} />
        </Root>
    );
};
