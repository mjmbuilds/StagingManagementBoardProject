import { Component, OnInit } from '@angular/core';
import { Board } from 'src/app/models/Board';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  activeBoard: Board;
  boardActive = false;
  boardID: string;
  boardTitle: string;

  constructor() { }

  ngOnInit(): void {
  }

  openBoard(board: Board) {
    this.activeBoard = board;
    this.boardID = board.id;
    this.boardTitle = board.title;
    this.boardActive = true;
  }

  closeBoard() {
    this.boardActive = false;
    this.activeBoard = null;
    this.boardID = null;
    this.boardTitle = null;
  }

}
