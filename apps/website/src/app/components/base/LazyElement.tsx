import { Component, ReactNode } from 'react';
import { Observable } from 'rxjs';

interface LazyEntityProps<Entity> {
    entity: Entity;
    mappingFn: (entity: Entity) => JSX.Element;
}
function LazyEntity<Entity>(props: LazyEntityProps<Entity>) {
    return props.mappingFn(props.entity);
}
export enum LazyOnEmitStrategy {
    APPEND,
    REPLACE,
}
export interface LazyElementProps<Entity> {
    stream: Observable<Entity>;
    mappingFn: (entity: Entity) => JSX.Element;
    onEmitStrategy: LazyOnEmitStrategy;
    sortingFn?: (a: Entity, b: Entity) => number;
}
interface LazyElementState<Entity> {
    elements: Entity[];
}
export class LazyElement<Entity> extends Component<
    LazyElementProps<Entity>,
    LazyElementState<Entity>
> {
    constructor(props: LazyElementProps<Entity>) {
        super(props);
        this.state = { elements: [] as Entity[] };
    }
    override componentDidMount() {
        this.props.stream.subscribe((element: Entity) => {
            console.log(element);
            let newElements: Entity[];
            switch (this.props.onEmitStrategy) {
                case LazyOnEmitStrategy.APPEND:
                    newElements = [...this.state.elements, element];
                    break;
                case LazyOnEmitStrategy.REPLACE:
                    newElements = [element];
                    break;
            }
            this.setState({ elements: this.doSorting(newElements) });
        });
    }
    private doSorting(list: Entity[]) {
        const sorting = this.props.sortingFn;
        if (!sorting) return list;
        return list.sort(sorting);
    }
    override render(): ReactNode {
        console.log(this.state);
        return this.state.elements.map((entity: Entity, i: number) => (
            <LazyEntity
                key={i}
                entity={entity}
                mappingFn={this.props.mappingFn}
            />
        ));
    }
}
