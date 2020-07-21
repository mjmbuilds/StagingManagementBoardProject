import { Component, OnInit } from '@angular/core';
import { Board } from 'src/app/models/Board';
import { Category } from 'src/app/models/Category';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  showBoard = false;
  boards: Board[];
  activeBoard: Board;
  boardID: string;
  boardTitle: string;
  categories: Category[];

  constructor(private userServ: UserService) { }

  ngOnInit(): void {
    if (this.userServ.isLoggedIn) {
      this.boards = this.userServ.loggedUser.boards;

      // hard coded open 1st board... should add selection later
      if (this.boards && this.boards.length >= 1) {
        this.openBoard(this.boards[1]);
      }
    }
  }

  openBoard(board: Board) {
    this.activeBoard = board;
    this.boardID = board.id;
    this.boardTitle = board.title;
    this.showBoard = true;
    if (board.categories) {
      this.categories = board.categories;
    }
  }

  closeBoard() {
    this.showBoard = false;
    this.activeBoard = null;
    this.boardID = null;
    this.boardTitle = null;
    this.categories = null;
  }

}
