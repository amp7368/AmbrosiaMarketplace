import { useEffect, useMemo, useState } from 'react';
import { distinctUntilChanged, map, Observable, tap } from 'rxjs';

export interface ObserveableToElementProps<T> {
    original: Observable<T>;
    mappingFn: (original: T) => JSX.Element | null;
}

interface ObserveableToElementState {
    currentElement?: JSX.Element;
}

export function observeableToElement<T>(props: ObserveableToElementProps<T>) {
    let state: ObserveableToElementState;
    let setState: (prevState: ObserveableToElementState) => void;
    [state, setState] = useState<ObserveableToElementState>({});
    const subscription = useMemo(() => {
        return props.original
            .pipe(
                distinctUntilChanged(),
                map(props.mappingFn),
                tap((newElement: any) => {
                    setState({
                        currentElement: newElement,
                    });
                })
            )
            .subscribe();
    }, [props.original]);
    useEffect(() => {
        return () => {
            subscription.unsubscribe();
        };
    }, [props.original]);
    if (state.currentElement == undefined) return null;
    return state.currentElement;
}
