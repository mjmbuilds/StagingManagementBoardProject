import { Component, OnInit } from '@angular/core';
import { Board } from 'src/app/models/Board';
import { Category } from 'src/app/models/Category';
import { Router } from '@angular/router';
import { AuthSimpleService } from 'src/app/services/auth-simple.service';
import { BoardService } from 'src/app/services/board.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  showBoard = false;
  boards: Board[];
  activeBoard: Board;
  selectedIndex: number;
  categories: Category[];
  boardTitle: string;
  boardTitleInput: string;
  editingTitle = false;
  showAddBoard = false;
  addBoardTitle: string;



  constructor(private router: Router, private boardServ: BoardService, private authServ: AuthSimpleService) { }

  ngOnInit(): void {
    if (this.authServ.hasLoggedInUser) {
      this.boards = this.authServ.loggedInUser.boards;

      this.selectBoard('0');//TODO remove after testing

    } else {
      this.router.navigateByUrl(''); // if not logged in, navigate to home page
    }
  }

  selectBoard(selected: string) {
    if (selected === '-1') {
      this.closeBoard();
    } else {
      this.selectedIndex = Number(selected);
      this.boardServ.getBoard(this.boards[selected].id).subscribe(
        resp => {
          if (resp.id) {
            this.openBoard(resp);
            const dropdown = document.getElementById('board-dropdown') as HTMLSelectElement;
            dropdown.selectedIndex = Number(selected) + 1;
          }
        }
      );
    }
  }

  openBoard(board: Board) {
    this.activeBoard = board;
    this.boardTitle = board.title;
    this.boardTitleInput = board.title;
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

  openAddBoard() {
    this.addBoardTitle = null;
    this.showAddBoard = true;
  }

  submitAddBoard() {
    this.boardServ.addBoard(this.addBoardTitle, this.authServ.loggedInUser.id).subscribe(
      resp => {
        if (resp.code === 0) {
          const newBoard = new Board();
          newBoard.id = resp.message;
          newBoard.owningUserId = this.authServ.loggedInUser.id;
          newBoard.title = this.addBoardTitle;
          const idx = this.boards.push(newBoard);
          this.selectBoard((idx - 1).toString());
          this.closeAddBoard();
        } else if (resp.code === -1) {
          alert('Error adding board');
        } else {
          alert('Error: Unknown response');
        }
      }
    );
  }

  closeAddBoard() {
    this.addBoardTitle = null;
    this.showAddBoard = false;
  }

  deleteBoard() {
    if (confirm('Do you really want to delete this board?')) {
      this.boardServ.deleteBoard(this.activeBoard.id).subscribe(
        resp => {
          if (resp.code === 0) {
            this.boards.splice(this.selectedIndex, 1);
            this.selectBoard('-1');
          } else if (resp.code === -1) {
            alert('Error deleting board');
          } else {
            alert('Error: Unknown response');
          }
        }
      );
    }
  }

  editBoardTitle() {
    this.editingTitle = true;
  }

  saveBoardTitle() {
    this.editingTitle = false;
    if (this.boardTitleInput !== this.boardTitle ) {
      this.boardServ.updateBoard(this.activeBoard.id, this.boardTitleInput).subscribe(
        resp => {
          if (resp.code === 0) {
            this.boardTitle = this.boardTitleInput;
            this.boards[this.selectedIndex].title = this.boardTitle;
          } else if (resp.code === -1) {
            alert('Error updating board title');
          } else {
            alert('Error: Unknown response');
          }
        }
      );
    }
  }

}
