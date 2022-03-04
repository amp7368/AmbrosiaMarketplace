import { createTheme, Theme as MuiTheme } from '@mui/material';

// used to add custom properties to the theme
declare module '@mui/material/styles' {
    // eslint-disable-next-line @typescript-eslint/no-empty-interface
    interface Theme {}
    // allow configuration using `createTheme`
    // eslint-disable-next-line @typescript-eslint/no-empty-interface
    interface ThemeOptions {}
}
declare module '@emotion/react' {
    // eslint-disable-next-line @typescript-eslint/no-empty-interface
    export interface Theme extends MuiTheme {}
}

export const appTheme = createTheme({
    palette: {
        primary: {
            light: '#c2f6d7',
            main: '##4d9365',
            dark: '#35704a',
            contrastText: '#043822',
        },
        secondary: {
            light: '#4d211a',
            main: '#301510',
            dark: '#1d0d0a',
            contrastText: '#4d9365',
        },
    },
});
