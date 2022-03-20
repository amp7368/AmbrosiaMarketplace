import { createTheme, Theme as MuiTheme } from '@mui/material';

// used to add custom properties to the theme
declare module '@mui/material/styles' {
    interface Theme {}
    // allow configuration using `createTheme`
    interface ThemeOptions {}
}
declare module '@emotion/react' {
    export interface Theme extends MuiTheme {}
}

export const appTheme = createTheme({
    palette: {
        mode: 'dark',
        primary: {
            main: '#2f1b6d',
        },
        secondary: {
            main: '#4fc3f7',
        },
        background: {
            default: '#0f032e',
        },
        text: {
            primary: '#ffffff',
            secondary: '#000000',
            disabled: '#696969',
        },
    },
});
