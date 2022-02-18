import styled from '@emotion/styled';
import { Component, useState } from 'react';
import { PropsAndClass } from '../../../util/PropUtils';
import { PageWrapper } from '../PageWrapper';
import { PageWrapperProps } from '../routeProps';
import { CatalogueContentWrapper } from './CatalogueContent';
import { AllDisplayTabs, DisplayTab } from './CatalogueDisplayTab';

interface CatalogueHeaderProps {
    setDisplayTab: (tab: DisplayTab) => void;
}

class CatalogueHeader extends Component<PropsAndClass<CatalogueHeaderProps>> {
    constructor(props: PropsAndClass<CatalogueHeaderProps>) {
        super(props);
        this.setDisplayTab = this.setDisplayTab.bind(this);
    }

    protected setDisplayTab(tab: DisplayTab) {
        this.props.innerProps.setDisplayTab(tab);
    }

    override render() {
        return (
            <>
                <div className={this.props.className}>
                    <p
                        onClick={() =>
                            this.setDisplayTab(AllDisplayTabs.UN_IDED)
                        }
                    >
                        setDisplayTyped
                    </p>
                </div>
                <div>
                    <p
                        onClick={() =>
                            this.setDisplayTab(AllDisplayTabs.STAT_ITEM)
                        }
                    >
                        setDisplayStat
                    </p>
                </div>
            </>
        );
    }
}

const StyledCatalogueHeader = styled(CatalogueHeader)(
    () => `
    height: 100%;
    width: 100%;
`
);

class CataloguePage extends PageWrapper {
    constructor(props: PageWrapperProps) {
        super(props);
        this.renderMainPage = this.renderMainPage.bind(this);
    }
    public renderMainPage = () => {
        const [displayTab, setDisplayTab] = useState<DisplayTab>(
            AllDisplayTabs.STAT_ITEM
        );

        return (
            <>
                <StyledCatalogueHeader
                    innerProps={{ setDisplayTab: setDisplayTab }}
                />
                <CatalogueContentWrapper
                    displayTab={displayTab}
                ></CatalogueContentWrapper>
            </>
        );
    };
}

export default CataloguePage;
