import { Route } from 'react-router-dom';
import { PageWrapperProps } from './routeProps';

export interface IPageWrapper {
    props: PageWrapperProps;
    PageElement(): JSX.Element;
}

export class RouteInfo {
    props: PageWrapperProps;
    page: IPageWrapper;
    constructor(page: IPageWrapper) {
        this.props = page.props;
        this.page = page;
        this.renderRoute = this.renderRoute.bind(this);
    }
    getName(): string {
        return this.props.title;
    }
    renderRoute({ key }: { key: number }): JSX.Element {
        return (
            <Route
                key={key}
                path={this.props.link}
                element={this.renderPage()}
            />
        );
    }
    renderPage(): JSX.Element {
        return this.page.PageElement();
    }
}
