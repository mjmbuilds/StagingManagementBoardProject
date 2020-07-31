import { Card } from './Card';

export class Category {
    id: string;
    owningBoardId: string;
    title: string;
    cards: Card[];
}
