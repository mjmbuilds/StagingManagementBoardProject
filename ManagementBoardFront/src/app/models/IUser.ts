import { Board } from './Board';

export interface IUser {
    firstName: string;
    lastName: string;
    username: string;
    password: string;
    boards: Board[];
}
