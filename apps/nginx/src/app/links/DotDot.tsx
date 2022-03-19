/* eslint-disable no-restricted-globals */
import { RedirectLink } from './RedirectLink';
export interface DotDotProps {
    name: string;
}
function onClick() {
    const splitHostname = location.hostname.split('(.-)');
    splitHostname.shift();
    location.hostname = splitHostname.join('.');
}
export const DotDot = (props: DotDotProps) => {
    return <RedirectLink onClick={onClick} {...props} />;
};
