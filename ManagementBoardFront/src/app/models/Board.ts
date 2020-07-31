import { Category } from './Category';

export class Board {
    id: string;
    owningUserId: string;
    title: string;
    categories: Category[];
}
