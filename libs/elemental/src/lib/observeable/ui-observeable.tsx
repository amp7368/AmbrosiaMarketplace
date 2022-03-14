import { useEffect, useMemo, useState } from 'react';
import { distinctUntilChanged, map, Observable } from 'rxjs';

type DisplayedElement = JSX.Element | null;

export interface ObserveableToElementProps<T> {
    original: Observable<T>;
    mappingFn: (original: T) => DisplayedElement;
}

export function ObserveableToElement<T>(
    props: ObserveableToElementProps<T>
): DisplayedElement {
    const [currentElement, setState] = useState<DisplayedElement>(null);
    const subscription = useMemo(() => {
        return props.original
            .pipe(distinctUntilChanged(), map(props.mappingFn))
            .subscribe(setState);
    }, [props.original, props.mappingFn]);
    useEffect(() => {
        return () => {
            subscription.unsubscribe();
        };
    }, [subscription]);
    return currentElement ?? null;
}
