import {
    Box,
    Button,
    Container,
    Stack,
    styled,
    Typography,
} from '@mui/material';
import { AppImg } from '../../../util/imageHandling';
import { DarkShadowText, LightShadowText } from '../../base/AppTypography';

const welcomeRow = (
    <Stack
        direction="row"
        padding={4}
        justifyContent="space-between"
        bgcolor="primary.light"
        alignItems="center"
    >
        <Box>
            <DarkShadowText fontSize="2rem" fontWeight="bold">
                Welcome to
                <br />
                Ambrosia Markets!
            </DarkShadowText>
        </Box>
        <Box height="8rem">{AppImg.logo().resize(true).build()}</Box>
    </Stack>
);
const loginRow = (
    <Stack direction="row" padding={4} justifyContent="center">
        <Button variant="contained" color="secondary">
            <Typography fontSize="2rem">Login</Typography>
        </Button>
    </Stack>
);
const signupRow = (
    <Stack
        direction="column"
        padding={4}
        justifyContent="space-between"
        alignItems="center"
    >
        <LightShadowText fontSize="3rem">Signup using</LightShadowText>
        <Stack width="100%" direction="row" justifyContent="space-between">
            <Button variant="contained" color="secondary">
                <Typography fontSize="1.5rem">Discord</Typography>
            </Button>
            <Button variant="contained" color="secondary">
                <Typography fontSize="1.5rem">Minecraft</Typography>
            </Button>
            <Button variant="contained" color="secondary">
                <Typography fontSize="1.5rem">Don't verify</Typography>
            </Button>
        </Stack>
    </Stack>
);
export function AuthSideDrawer() {
    return (
        <Stack direction="column" justifyContent="space-between">
            {welcomeRow}
            {loginRow}
            {signupRow}
        </Stack>
    );
}
