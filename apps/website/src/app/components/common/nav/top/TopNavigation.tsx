import {
    AppBar,
    Box,
    Button,
    Container,
    Paper,
    Stack,
    Typography,
} from '@mui/material';

import { RouteInfo } from '../../../../../app/routes/RouteInfo';
import { AllRoutes } from '../../../../../app/routes/routes';
import { AppImg } from '../../../../util/imageHandling';

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

export const TopNavigation = () => {
    const HomeButton = (
        <AppBarLink
            route={AllRoutes.HomeRoute}
            render={() => (
                // TODO: clean up magic
                <Box
                    width="12rem"
                    height="12rem"
                    margin="-3rem"
                    marginLeft="-3rem"
                >
                    {AppImg.logo().resize(true).build()}
                </Box>
            )}
        />
    );
    let buttons = [AllRoutes.HomeRoute, AllRoutes.ProfileRoute].map(
        (route, i) => <AppBarLink key={i} route={route} />
    );
    buttons = [HomeButton].concat(buttons);
    return (
        <AppBar position="static" color="primary" enableColorOnDark={true}>
            <Stack
                direction="row"
                bgcolor="primary"
                spacing="1"
                justifyContent="space-between"
                padding="2rem"
            >
                {buttons.map((button) => (
                    <Box width="5rem" height="5rem">
                        {button}
                    </Box>
                ))}
                <Box />
            </Stack>
        </AppBar>
    );
};
