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
  showSignup = false;
  activeBoard: Board;
  boardID: string;
  boardTitle: string;
  categories: Category[];
  signupFirstname: string;
  signupLastname: string;
  signupUsername: string;
  signupPassword: string;

  constructor(private userService: UserService) { }

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
    if (this.signupFirstname && this.signupLastname && this.signupUsername && this.signupPassword) {
      this.userService.signup(this.signupFirstname, this.signupLastname, this.signupUsername, this.signupPassword);
      this.closeSignup();
    }
  }

}
