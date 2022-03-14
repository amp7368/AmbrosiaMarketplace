import { PropsWithChildren } from 'react';
import styled from '@emotion/styled';

export function divWrapperWith(
    RenderFirstChild: any,
    ...childs: any[]
): (props: PropsWithChildren<any>) => JSX.Element {
    if (childs === undefined || childs.length === 0) {
        return (props) => {
            return (
                <RenderFirstChild {...props}>{props.children}</RenderFirstChild>
            );
        };
    }
    const childsRest = childs.slice(1, childs.length);
    const InsideChild = divWrapperWith(childs[0], ...childsRest);
    return (props) => {
        return (
            <RenderFirstChild>
                <InsideChild {...props} />
            </RenderFirstChild>
        );
    };
}

export const styledDivWrapper: (
    props: PropsWithChildren<{ className?: string }>
) => JSX.Element = (props) => {
    return <div className={props.className}>{props.children}</div>;
};
export function insidePadding(padding: string) {
    return styled(styledDivWrapper)`
        padding: ${padding};
        width: calc(100% - 2 * ${padding});
        height: calc(100% - 2 * ${padding});
    `;
}
export const StyledDiv100 = styled(styledDivWrapper)`
    width: 100%;
    height: 100%;
`;
