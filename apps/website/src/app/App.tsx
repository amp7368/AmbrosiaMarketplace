import { PropsJustChildren } from '@appleptr16/elemental';
import { Box, ThemeProvider, Typography } from '@mui/material';
import { BrowserRouter, Routes } from 'react-router-dom';

import { appTheme } from './util/themeManager';
import { AllRoutes } from './routes/routes';

const Root = (props: PropsJustChildren) => (
    <ThemeProvider theme={appTheme}>
        <Box width="100vw" height="100vh" bgcolor="background.default">
            {props.children}
        </Box>
    </ThemeProvider>
);
function App() {
    const routes = Object.values(AllRoutes).map((route, i) =>
        route.renderRoute({ key: i })
    );
    return (
        <Root>
            <BrowserRouter>
                <Routes>{routes}</Routes>
            </BrowserRouter>
        </Root>
    );
}

export default App;
