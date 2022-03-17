import { Query } from '@datorama/akita';
import { map, Observable } from 'rxjs';
export class QueryBase<State> extends Query<State> {
    selected: Observable<State> = this.select();

    selectKey<T extends keyof State>(key: T): Observable<State[T]> {
        return this.selectKeyFrom(this.selected, key);
    }

    selectKeyFrom<O, T extends keyof O>(
        obsv: Observable<O>,
        key: T
    ): Observable<O[T]> {
        return obsv.pipe(map((element: O) => element[key]));
    }
    map<O, T>(obsv: Observable<O>, mappingFn: (o: O) => T): Observable<T> {
        return obsv.pipe(map(mappingFn));
    }
}
