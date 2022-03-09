import { Theme } from '@mui/material';

export type StyledStylingProps<
    ExcludedProps = Record<string, never>,
    ForwardedProps = Record<string, never>
> = ForwardedProps & ExcludedProps & { theme: Theme };
