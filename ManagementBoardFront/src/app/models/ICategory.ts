import { ICard } from './ICard';

export interface ICategory {
    id: string;
    title: string;
    cards: ICard[];
}
