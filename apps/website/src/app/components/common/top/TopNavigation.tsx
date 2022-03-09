import {
    AppBar,
    Box,
    Button,
    Container,
    CssBaseline,
    Paper,
    Stack,
    Toolbar,
    Typography,
} from '@mui/material';

import { RouteInfo } from '../../../routes/RouteInfo';
import { AllRoutes } from '../../../routes/routes';
import { AppImg } from '../../../util/imageHandling';

interface AppBarLinkProps {
    route: RouteInfo;
    render?: (name: string) => JSX.Element;
}
function AppBarLink({ route, render }: AppBarLinkProps): JSX.Element {
    const changeRoute = () => window.location.replace(route.props.link);
    return (
        <Button variant="text" onClick={changeRoute}>
            <Typography variant="h2" color="text.primary" fontStyle={{}}>
                {render ? render(route.getName()) : route.getName()}
            </Typography>
        </Button>
    );
}
const renderHomeButton = () => (
    // TODO: clean up magic
    <Box width="12rem" height="12rem" margin="-3rem" marginLeft="-3rem">
        {AppImg.logo().resize(true).build()}
    </Box>
);

export function TopNavigation({ hidden }: { hidden: boolean }) {
    const HomeButton = (
        <AppBarLink
            key="home"
            route={AllRoutes.HomeRoute}
            render={renderHomeButton}
        />
    );
    let buttons = [
        AllRoutes.ProfileRoute,
        AllRoutes.ProfileRoute,
        AllRoutes.ProfileRoute,
        AllRoutes.ProfileRoute,
    ].map((route, i) => <AppBarLink key={i} route={route} />);
    buttons = [HomeButton, ...buttons].map((button, i) => (
        <Box width="5rem" height="5rem" key={i}>
            {button}
        </Box>
    ));
    return (
        <Stack
            {...(hidden && { display: 'hidden' })}
            width="100vw"
            direction="row"
            bgcolor="primary.main"
            spacing="1"
            justifyContent="space-between"
            padding="2rem"
            sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}
        >
            {buttons}
            <Box />
        </Stack>
    );
}
