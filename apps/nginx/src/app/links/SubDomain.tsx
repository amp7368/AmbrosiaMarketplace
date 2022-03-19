/* eslint-disable no-restricted-globals */
import { RedirectLink } from './RedirectLink';

export interface SubDomainProps {
    newSubdomain: string;
    name: string;
}
function onClick(props: SubDomainProps) {
    location.hostname = `${props.newSubdomain}-${location.hostname}`;
}
export const SubDomain = (props: SubDomainProps) => {
    return (
        <RedirectLink
            onClick={() => onClick(props)}
            name={props.name + '-' + location.hostname}
        ></RedirectLink>
    );
};
