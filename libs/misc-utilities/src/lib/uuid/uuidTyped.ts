import { parse, stringify, v4 } from 'uuid';
export type uuidv4 = number[] | undefined;
export function uuidv4Parse(uuid: string): uuidv4 {
    // uuid = uuid.replace('-', '');
    const bytes = parse(uuid);
    return Array.from(bytes);
}
