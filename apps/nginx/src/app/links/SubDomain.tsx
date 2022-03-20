import { Button, Container, Typography } from '@mui/material';

export interface SubDomainProps {
    newSubdomain: string;
    name: string;
}
function setDomain(newSubdomain: string) {
    return (location.hostname = `${newSubdomain}.${location.hostname}`);
}
export function SubDomain({ newSubdomain, name }: SubDomainProps) {
    return (
        <Button
            color="secondary"
            variant="contained"
            onClick={() => setDomain(newSubdomain)}
        >
            <Typography>
                {newSubdomain}.{location.hostname}
            </Typography>
        </Button>
    );
}
