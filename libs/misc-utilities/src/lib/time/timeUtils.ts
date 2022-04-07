import { uppercaseFirst } from '../formatStrings/formatStrings';

export function hoursToMillis(hours: number) {
    return minutesToMillis(hours * 60);
}
export function minutesToMillis(minutes: number) {
    return secondsToMillis(minutes * 60);
}
export function secondsToMillis(seconds: number) {
    return seconds * 1000;
}
type SetFn = `set${string}`;
type SetDateUnitFn = keyof {
    [Key in keyof Date as Key extends SetFn ? Key : never]: unknown;
};
type ExtractUnitFromSet<SetUnit> = SetUnit extends `set${infer Field}`
    ? Field
    : never;

type SetDateFn = (amount: number) => void;
type GetDateFn = () => number;

export type DateUnit = Uncapitalize<ExtractUnitFromSet<SetDateUnitFn>>;
export function dateAddUnits(date: Date, unit: DateUnit, amount: number): Date {
    const newDate = new Date(date);
    const setFn: SetDateFn = newDate[`set${uppercaseFirst(unit)}`] as SetDateFn;
    const getFn: GetDateFn = newDate[`get${uppercaseFirst(unit)}`] as GetDateFn;
    setFn(amount + getFn());
    return newDate;
}
export class DateFactory {
    addDays(date: Date, amount: number): Date {
        return dateAddUnits(date, 'date', amount);
    }
    addHours(date: Date, amount: number): Date {
        return dateAddUnits(date, 'hours', amount);
    }
    addMinutes(date: Date, amount: number): Date {
        return dateAddUnits(date, 'minutes', amount);
    }
    addSeconds(date: Date, amount: number): Date {
        return dateAddUnits(date, 'seconds', amount);
    }
    addMillis(date: Date, amount: number): Date {
        return dateAddUnits(date, 'milliseconds', amount);
    }

    fromNowDays(amount: number): Date {
        return this.addDays(new Date(), amount);
    }
    fromNowHours(amount: number): Date {
        return this.addHours(new Date(), amount);
    }
    fromNowMinutes(amount: number): Date {
        return this.addMinutes(new Date(), amount);
    }
    fromNowSeconds(amount: number): Date {
        return this.addSeconds(new Date(), amount);
    }
    fromNowMillis(amount: number): Date {
        return this.addMillis(new Date(), amount);
    }
}
export const dateFactory = new DateFactory();
