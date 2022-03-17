import { Box, FormControl, Stack, TextField } from '@mui/material';
import { useForm } from 'react-hook-form';
import { sessionService } from '../../../model/session/Session.service';

import { AuthLoginButton } from './AuthLoginButton';

function onValidFormSubmit() {
    sessionService.login({ username: 'appleptr16', password: 'appleptr16' });
}
export function LoginForm() {
    const { register, handleSubmit, formState } = useForm();
    return (
        <form>
            <Stack direction="column">
                <Box>
                    <FormControl color="secondary">
                        <TextField
                            {...register('username')}
                            required={true}
                            autoFocus
                            inputMode="text"
                            focused
                            variant="filled"
                            placeholder="Username"
                        />
                    </FormControl>
                </Box>
                <Box>
                    <TextField
                        {...register('password')}
                        required={true}
                        autoFocus
                        inputMode="text"
                        type="password"
                        focused
                        variant="filled"
                        placeholder="Password"
                    />
                </Box>
                <Stack direction="row" padding={4} justifyContent="center">
                    <AuthLoginButton
                        onClick={handleSubmit(onValidFormSubmit)}
                    />
                </Stack>
            </Stack>
        </form>
    );
}
