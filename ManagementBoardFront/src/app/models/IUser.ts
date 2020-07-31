import { Board } from './Board';

export interface IUser {
    id: string;
    firstName: string;
    lastName: string;
    username: string;
    password: string;
    boards: Board[];
}
