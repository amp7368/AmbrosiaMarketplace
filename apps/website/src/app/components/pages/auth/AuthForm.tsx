import { Button, Stack, Typography } from '@mui/material';

export function AuthForm() {
    return (
        <form>
            <Stack direction="row" padding={4} justifyContent="center">
                <Button variant="contained" color="secondary">
                    <Typography fontSize="2rem">Login</Typography>
                </Button>
            </Stack>
        </form>
    );
}
