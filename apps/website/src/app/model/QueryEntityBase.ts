import {
    EntityState,
    EntityStore,
    getEntityType,
    QueryEntity,
} from '@datorama/akita';
import { Predicate } from '@appleptr16/utilities';
import { map, Observable } from 'rxjs';

export class QueryEntityBase<
    State extends EntityState<any, any>,
    EntityType = getEntityType<State>
> extends QueryEntity<State> {
    filterThisBy(filterBy: Predicate<EntityType>): Observable<EntityType[]> {
        return this.filterBy(this.selectAll(), filterBy);
    }
    filterBy<T>(
        obsv: Observable<T[]>,
        filterBy: (hunt: T) => boolean
    ): Observable<T[]> {
        return obsv.pipe(map((hunts: T[]) => hunts.filter(filterBy)));
    }
    filterByWithExtract<Original, Extracted>(
        obsv: Observable<Original>,
        extractArray: (original: Original) => Extracted[],
        filterBy: (element: Extracted, original: Original) => boolean
    ): Observable<Extracted[]> {
        return obsv.pipe(
            map((original: Original) => {
                const extractedArray: Extracted[] = extractArray(original);
                return extractedArray.filter((element: Extracted) =>
                    filterBy(element, original)
                );
            })
        );
    }
}
