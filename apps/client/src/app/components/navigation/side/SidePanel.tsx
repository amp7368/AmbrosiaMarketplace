import styled from '@emotion/styled';
import { CSSConsts } from '../../../util/CSSConsts';
import { PresetOverlap100Container, PresetOverlap100Element } from 'elemental';
import {
    ImgSidePanelBackground,
    ImgSidePanelChains,
} from '../../../util/imageHandling';
const StyledRoot = styled(PresetOverlap100Container)`
    width: ${CSSConsts.SidePanel.width};
    height: 100%;
`;

export const SidePanel = () => {
    return (
        <StyledRoot>
            <PresetOverlap100Element>
                {ImgSidePanelBackground}
            </PresetOverlap100Element>
            <PresetOverlap100Element>
                {ImgSidePanelChains}
            </PresetOverlap100Element>
            <PresetOverlap100Element></PresetOverlap100Element>
        </StyledRoot>
    );
};
