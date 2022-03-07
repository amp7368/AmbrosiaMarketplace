import { Component, ReactNode, useState, useEffect } from 'react';
import { Observable } from 'rxjs';

export interface LazyOutputProps<MapTo> {
    entities: MapTo[];
}
export enum LazyOnEmitStrategy {
    APPEND,
    REPLACE,
}
export interface LazyElementPropsAsMapped<Entity, MapTo> {
    stream: Observable<Entity>;
    mappingFn: (entity: Entity, i: number) => MapTo;
    onEmitStrategy: LazyOnEmitStrategy;
    sortingFn?: (a: Entity, b: Entity) => number;
    wrapper?: (props: LazyOutputProps<MapTo>) => JSX.Element;
}
interface LazyElementState<Entity> {
    elements: Entity[];
}
function doSorting<Entity>(
    elements: Entity[],
    element: Entity,
    onEmitStrategy: LazyOnEmitStrategy,
    sortingFn?: (a: Entity, b: Entity) => number
) {
    let newElements: Entity[];
    switch (onEmitStrategy) {
        case LazyOnEmitStrategy.APPEND:
            newElements = [...elements, element];
            break;
        case LazyOnEmitStrategy.REPLACE:
            newElements = [element];
            break;
    }
    if (sortingFn) newElements.sort(sortingFn);
    return newElements;
}
export function LazyElement<Entity, MapTo>(
    props: LazyElementPropsAsMapped<Entity, MapTo>
) {
    const [state, setState] = useState({
        elements: [] as Entity[],
    });
    useEffect(() => {
        const subscription = props.stream.subscribe((element: Entity) => {
            const newElements: Entity[] = doSorting(
                state.elements,
                element,
                props.onEmitStrategy,
                props.sortingFn
            );
            setState({ elements: newElements } as LazyElementState<Entity>);
        });
        return () => subscription.unsubscribe();
    }, [props.onEmitStrategy, props.sortingFn, props.stream, state.elements]);

    const mapped: MapTo[] = state.elements.map(props.mappingFn);
    if (!props.wrapper) return mapped;
    return <props.wrapper entities={mapped}></props.wrapper>;
}
