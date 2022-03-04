import { ReactNode } from 'react';

import { RouteInfo } from '../../../routes/RouteInfo';
import { PageWrapper } from '../PageWrapper';

export class HomePage extends PageWrapper {
    createRoute(): RouteInfo {
        return new RouteInfo(this);
    }

    renderMainPage(): ReactNode {
        return <h1>Main Page</h1>;
    }
    sideBar(): ReactNode {
        return <h2>sidebar</h2>;
    }
}
