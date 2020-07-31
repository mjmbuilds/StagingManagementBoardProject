import { Board } from './Board';

export class User {
    id: string;
    firstName: string;
    lastName: string;
    username: string;
    password: string;
    boards: Board[];
}
