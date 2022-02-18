import { BrowserRouter, Routes } from 'react-router-dom';
import { AllRoutesList, RouteInfo } from './components/pages/routes';

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
