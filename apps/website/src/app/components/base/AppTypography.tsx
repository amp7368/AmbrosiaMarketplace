import { styled, Typography } from '@mui/material';

export interface ShadowTextProps {
    shadowSize?: number;
}
export const DarkShadowText = styled(Typography)<ShadowTextProps>(
    ({ theme, shadowSize = 25 }) => ({
        textShadow: `${theme.palette.common.black} 0 0 ${shadowSize}px`,
    })
);
export const LightShadowText = styled(Typography)<ShadowTextProps>(
    ({ theme, shadowSize = 5 }) => {
        const color1 = theme.palette.common.black;
        const color2 = theme.palette.common.black;
        const color3 = theme.palette.common.white;
        const size1 = shadowSize;
        const size2 = shadowSize;
        const size3 = shadowSize * 1.5;
        return {
            textShadow: `${color1} 0 0 ${size1}px, ${color2} 0 0 ${size2}px, ${color3} 0 0 ${size3}px`,
        };
    }
);
