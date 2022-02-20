import { BrowserRouter, Routes } from 'react-router-dom';
import { RouteInfo } from './routes/RouteInfo';
import { AllRoutesList } from './routes/routes';

function App() {
    const routes: JSX.Element[] = [];
    let route: RouteInfo;
    let i = 0;
    for (route of AllRoutesList) {
        routes.push(route.toRoute({ key: i++ }));
    }
    return (
        <BrowserRouter>
            <Routes>{routes}</Routes>
        </BrowserRouter>
    );
}

export default App;
