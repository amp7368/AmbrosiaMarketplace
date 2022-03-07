import { PropsJustChildren } from '@appleptr16/elemental';
import { Box, ThemeProvider } from '@mui/material';
import { BrowserRouter, Routes } from 'react-router-dom';

import { appTheme } from './util/themeManager';
import { AllRoutes } from './routes/routes';

const StyledRoot = (props: PropsJustChildren) => (
    <Box width="100vw" height="100vh" bgcolor="background.default">
        {props.children}
    </Box>
);
function App() {
    const routes = Object.values(AllRoutes).map((route, i) =>
        route.renderRoute({ key: i })
    );
    return (
        <ThemeProvider theme={appTheme}>
            <StyledRoot>
                <BrowserRouter>
                    <Routes>{routes}</Routes>
                </BrowserRouter>
            </StyledRoot>
        </ThemeProvider>
    );
}

export default App;
