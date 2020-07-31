import { Component, OnInit } from '@angular/core';
import { Board } from 'src/app/models/Board';
import { Category } from 'src/app/models/Category';
import { Router } from '@angular/router';
import { AuthSimpleService } from 'src/app/auth-simple.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  showBoard = false;
  boards: Board[];
  activeBoard: Board;
  boardTitle: string;
  categories: Category[];

  constructor(private router: Router, private authServ: AuthSimpleService) { }

  ngOnInit(): void {
    if (this.authServ.hasLoggedInUser) {
      this.boards = this.authServ.loggedInUser.boards;

      //TODO remove after development (temporary setting of first board on load)
      //if (this.boards && this.boards.length >= 1) {
      //  this.selectBoard('0');
      //}

    } else {
      this.router.navigateByUrl(''); // if not logged in, navigate to home page
    }
  }

  selectBoard(selected: string) {
    if (selected === '-1') {
      this.closeBoard();
    } else {
      this.openBoard(this.boards[selected]);
    }
  }

  openBoard(board: Board) {
    this.activeBoard = board;
    this.boardTitle = board.title;
    this.showBoard = true;
    if (board.categories) {
      this.categories = board.categories;
    }
  }

  closeBoard() {
    this.showBoard = false;
    this.activeBoard = null;
    this.boardTitle = null;
    this.categories = null;
  }

}
