import { Component, OnInit } from '@angular/core';
import { Board } from 'src/app/models/Board';
import { Category } from 'src/app/models/Category';
import { Router } from '@angular/router';
import { AuthSimpleService } from 'src/app/services/auth-simple.service';
import { BoardService } from 'src/app/services/board.service';
import { CategoryService } from 'src/app/services/category.service';
import { IndexList } from 'src/app/models/indexList';
import { CardService } from 'src/app/services/card.service';

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
  showAddCategory = false;
  addCategoryTitle: string;
  draggedCategoryIndex = -1;
  draggedCardIndex = -1;
  dragoverCategoryIndex = -1;
  dragoverCardIndex = -1;


  constructor(private router: Router, private boardServ: BoardService,
              private categoryServ: CategoryService,
              private cardServ: CardService,
              private authServ: AuthSimpleService) { }

  ngOnInit(): void {
    if (this.authServ.hasLoggedInUser) {
      this.boards = this.authServ.loggedInUser.boards;

      this.selectBoard('0');//TODO remove after testing

    } else {
      this.router.navigateByUrl(''); // if not logged in, navigate to home page
    }
  }

  setDraggedCategoryIndex(index: number) {
    this.draggedCategoryIndex = index;
    //console.log('drag category: ' + index);//debug
  }

  setDraggedCardIndex(index: number) {
    this.draggedCardIndex = index;
    //console.log('drag card: ' + index);//debug
  }

  setDragoverCategoryIndex(index: number) {
    this.dragoverCategoryIndex = index;
    //console.log('drag over category: ' + index);//debug
  }

  setDragoverCardIndex(index: number) {
    this.dragoverCardIndex = index;
    //console.log('drag over card: ' + index);//debug
  }

  onDrop() { //TODO
    if (this.draggedCardIndex >= 0) { // check if card was dragged
      if (this.dragoverCategoryIndex === this.draggedCategoryIndex
          && this.dragoverCardIndex === this.draggedCardIndex) {
        // if card is dropped on itself, cancel drop
        console.log('cancelled drop card');//debug
      } else {
        //console.log('dropped card');//debug
        const toCards = this.categories[this.dragoverCategoryIndex].cards; // card array dropped in
        const fromCards = this.categories[this.draggedCategoryIndex].cards; // card array dragged from
        const tempCard = fromCards[this.draggedCardIndex]; // ref to card being moved
        fromCards.splice(this.draggedCardIndex, 1); // remove card from old position
        toCards.splice(this.dragoverCardIndex, 0, tempCard); // insert card into new position
        if (this.dragoverCategoryIndex !== this.draggedCategoryIndex) {
          // if card was from different category then update index fields for cards in old category
          this.updateCardIndexes(this.draggedCategoryIndex);
          // also update moved card's category reference
          tempCard.owningCategoryId = this.categories[this.dragoverCategoryIndex].id;
          this.cardServ.updateCardCategory(tempCard.id, tempCard.owningCategoryId).subscribe(
            resp => {
              if (resp.code === 0) {
                // success updating card catgory
              } else if (resp.code === -1) {
                alert('Error updating card catgory');
              } else {
                alert('Error: Unknown response');
              }
            }
          );
        }
        // update index fields for cards in the drop category
        this.updateCardIndexes(this.dragoverCategoryIndex);
      }
    } else { // category was dragged
      if (this.dragoverCategoryIndex === this.draggedCategoryIndex) {
        // if category is dropped on itself, cancel drop
        console.log('cancelled dropp category');//debug
      } else {
        //console.log('dropped category');//debug
        const tempCategory = this.categories[this.draggedCategoryIndex]; // ref to category being moded
        this.categories.splice(this.draggedCategoryIndex, 1); // remove category from old position
        this.categories.splice(this.dragoverCategoryIndex, 0, tempCategory); // insert category into new position
        this.updateCategoryIndexes(); // update index fields for the categories
      }
    }
    this.draggedCategoryIndex = -1;
    this.draggedCardIndex = -1;
    this.dragoverCategoryIndex = -1;
    this.dragoverCardIndex = -1;
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

  compareCategory( a: Category, b: Category ) {
    if ( a.index < b.index ) {
      return -1;
    }
    if ( a.index > b.index ) {
      return 1;
    }
    return 0;
  }

  openBoard(board: Board) {
    this.activeBoard = board;
    this.boardTitle = board.title;
    this.boardTitleInput = board.title;
    this.showBoard = true;
    if (board.categories) {
      this.categories = board.categories;
      this.categories.sort(this.compareCategory);
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
    if (this.boardTitleInput && this.boardTitleInput !== this.boardTitle ) {
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

  openAddCategory() {
    this.addCategoryTitle = null;
    this.showAddCategory = true;
  }

  closeAddCategory() {
    this.addCategoryTitle = null;
    this.showAddCategory = false;
  }

  submitAddCategory() {
    if (this.categories == null) {
      this.categories = [];
    }
    this.categoryServ.addCategory(this.addCategoryTitle, this.activeBoard.id, this.categories.length).subscribe(
      resp => {
        if (resp.code === 0) {
          const newCategory = new Category();
          newCategory.id = resp.message;
          newCategory.owningBoardId = this.activeBoard.id;
          newCategory.index = this.categories.length;
          newCategory.title = this.addCategoryTitle;
          this.categories.push(newCategory);
          this.closeAddCategory();
        } else if (resp.code === -1) {
          alert('Error adding category');
        } else {
          alert('Error: Unknown response');
        }
      }
    );
  }

  deleteCategory(id: string, index: number) {
    if (confirm('Do you really want to delete this category?')) {
      this.categoryServ.deleteCategory(id).subscribe(
        resp => {
          if (resp.code === 0) {
            this.categories.splice(index, 1);
            this.updateCategoryIndexes();
          } else if (resp.code === -1) {
            alert('Error deleting category');
          } else {
            alert('Error: Unknown response');
          }
        }
      );
    }
  }

  // Step through a collection of Categories
  // and update their 'index' field, then add their
  // id to an IndexList, and send to DB to save
  updateCategoryIndexes() {
    const indexList = new IndexList(); // IndexList to be sent to DB
    indexList.idList = [];
    for (let i = 0; i < this.categories.length; i++) { // step through categories
      this.categories[i].index = i; // update 'index' field for the category
      indexList.idList.push(this.categories[i].id); // add category's id to the IndexList
    } // send update to server
    this.categoryServ.updateCategoryIndexList(indexList).subscribe(
      resp => {
        if (resp.code === 0) {
          // update was successful
        } else if (resp.code === -1) {
          alert('Error updating category index list');
        } else {
          alert('Error: Unknown response');
        }
      }
    );
  }

  deleteCard(id: string, cardIndex: number, categoryIndex: number) {
    if (confirm('Do you really want to delete this card?')) {
      this.cardServ.deleteCard(id).subscribe(
        resp => {
          if (resp.code === 0) {
            this.categories[categoryIndex].cards.splice(cardIndex, 1);
            this.updateCardIndexes(categoryIndex);
          } else if (resp.code === -1) {
            alert('Error deleting card');
          } else {
            alert('Error: Unknown response');
          }
        }
      );
    }
  }

  // Step through a collection of Cards
  // and update their 'index' field, then add their
  // id to an IndexList, and send to DB to save
  updateCardIndexes(categoryIndex: number) {
    const indexList = new IndexList(); // IndexList to be sent to DB
    indexList.idList = [];
    const cards = this.categories[categoryIndex].cards;
    for (let i = 0; i < cards.length; i++) { // step through cards
      cards[i].index = i; // update 'index' field for the card
      indexList.idList.push(cards[i].id); // add card's id to the IndexList
    } // send update to server
    this.cardServ.updateCardIndexList(indexList).subscribe(
      resp => {
        if (resp.code === 0) {
          // update was successful
        } else if (resp.code === -1) {
          alert('Error updating card index list');
        } else {
          alert('Error: Unknown response');
        }
      }
    );
  }

}
