import { Component, OnInit } from '@angular/core';
import { Board } from 'src/app/models/Board';
import { Category } from 'src/app/models/Category';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  showBoard = false;
  showSignup = false;
  activeBoard: Board;
  boardID: string;
  boardTitle: string;
  categories: Category[];
  signupFirstname: string;
  signupLastname: string;
  signupUsername: string;
  signupPassword: string;

  constructor() { }

  ngOnInit(): void {
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

  openSignup() {
    this.showSignup = true;
  }

  closeSignup() {
    this.showSignup = false;
  }

  submitSignup() {
    // TODO
  }

}
