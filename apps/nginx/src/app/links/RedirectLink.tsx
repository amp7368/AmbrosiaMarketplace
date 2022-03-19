export interface RedirectProps {
    name: string;
    onClick: () => void;
}

export const RedirectLink = (props: RedirectProps) => {
    return (
        <a onClick={props.onClick}>
            <h1>{props.name}</h1>
        </a>
    );
};
