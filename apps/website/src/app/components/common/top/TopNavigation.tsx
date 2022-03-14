import { Box, Button, Container, Stack, Typography } from '@mui/material';

import { RouteInfo } from '../../../routes/RouteInfo';
import { AllRoutes } from '../../../routes/routes';
import { AppImg } from '../../../util/imageHandling';
import { LightShadowText } from '../../base/AppTypography';

interface AppBarLinkProps {
    route: RouteInfo;
    render?: (name: string) => JSX.Element;
}
function AppBarLink({ route, render }: AppBarLinkProps): JSX.Element {
    const changeRoute = () => window.location.replace(route.props.link);
    return (
        <Button variant="text" onClick={changeRoute}>
            <LightShadowText variant="h2" color="text.primary" fontStyle={{}}>
                {render ? render(route.getName()) : route.getName()}
            </LightShadowText>
        </Button>
    );
}
const renderHomeButton = () => (
    // TODO: clean up magic
    <Box width="10rem" height="10rem" margin="-4rem" marginLeft="-3rem">
        {AppImg.logo().resize(true).build()}
    </Box>
);

export function TopNavigation() {
    const HomeButton = (
        <AppBarLink
            key="home"
            route={AllRoutes.Home}
            render={renderHomeButton}
        />
    );
    let buttons = [AllRoutes.Auth, AllRoutes.Profile].map(
        (route, i) => <AppBarLink key={i} route={route} />
    );
    buttons = [HomeButton, ...buttons].map((button, i) => (
        <Box width="5rem" height="5rem" key={i}>
            {button}
        </Box>
    ));
    return (
        <Stack
            width="100vw"
            padding="2rem"
            boxSizing="border-box"
            direction="row"
            bgcolor="primary.main"
            spacing="1"
            justifyContent="space-between"
        >
            {buttons}
            <Box />
        </Stack>
    );
}
