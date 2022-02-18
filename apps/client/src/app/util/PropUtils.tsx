import { ReactNode } from 'react';

export type PropsAndClass<Props> = {
    children?: any;
    className?: string;
    innerProps: Props;
};
export type PropsNoClass<Props> = { innerProps: Props };
export type PropsNoClassElement<Props> = (
    props: PropsNoClass<Props>
) => JSX.Element;

export type CreateElement<Props> = (props: Props) => JSX.Element;

type CreateElementProps = {
    children?: undefined | ReactNode;
};
export type CreateElementSimple = CreateElement<CreateElementProps>;
