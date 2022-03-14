import { Button, ButtonProps, Typography } from '@mui/material';
export type AuthLoginButtonProps = ButtonProps;

export function AuthLoginButton(props: AuthLoginButtonProps) {
    return (
        <Button variant="contained" color="secondary" {...props}>
            <Typography fontSize="2rem">Login</Typography>
        </Button>
    );
}
