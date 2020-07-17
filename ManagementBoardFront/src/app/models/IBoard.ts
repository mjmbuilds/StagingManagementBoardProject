import { ICategory } from './ICategory';

export interface IBoard {
    id: string;
    title: string;
    categories: ICategory[];
}
