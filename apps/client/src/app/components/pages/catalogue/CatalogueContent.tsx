import styled from '@emotion/styled';
import { ReactElement, useState } from 'react';
import { PropsAndClass } from '../../../util/PropUtils';
import { DisplayTab } from './CatalogueDisplayTab';

interface CatalogueContentProps {
    displayTab: DisplayTab;
}

const CatalogueContent = (props: PropsAndClass<CatalogueContentProps>) => {
    const [content, setContent] = useState<ReactElement | null>(null);
    props.innerProps.displayTab
        .getItems()
        .subscribe((content) => setContent(content));
    return <div className={props.className}>{content}</div>;
};

const StyledCatalogueContent = styled(CatalogueContent)(
    ({ theme }) => `
    height: 100%;
    width: 100%;
    background-color: ${theme.palette.primary.dark};
`
);

export const CatalogueContentWrapper = (props: CatalogueContentProps) => {
    return (
        <StyledCatalogueContent innerProps={{ displayTab: props.displayTab }} />
    );
};
