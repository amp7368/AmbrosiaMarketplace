import { BrowserRouter, Routes } from 'react-router-dom';

import { AllRoutes } from './routes/routes';

function App() {
    const routes = Object.values(AllRoutes).map((route, i) =>
        route.renderRoute(i)
    );
    return (
        <BrowserRouter>
            <Routes>{routes}</Routes>
        </BrowserRouter>
    );
}

export default App;
