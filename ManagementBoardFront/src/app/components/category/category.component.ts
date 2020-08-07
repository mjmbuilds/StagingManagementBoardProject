import { Component, OnInit, Input, Host } from '@angular/core';
import { Category } from 'src/app/models/Category';
import { Card } from 'src/app/models/Card';
import { CategoryService } from 'src/app/services/category.service';
import { BoardComponent } from '../board/board.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  @Input() category: Category;
  title = 'Category';
  titleInput: string;
  editingTitle = false;
  cards: Card[];

  constructor(@Host() private boardComp: BoardComponent, private categoryServ: CategoryService) {  }

  ngOnInit(): void {
    if (this.category) {
      this.title = this.category.title;
      this.titleInput = this.title;
      this.cards = this.category.cards;
      this.cards.sort(this.compareCard);
    }
  }

  dragCategoryStart(ev: Event) {
    this.boardComp.setDraggedCategoryIndex(this.category.index);
  }

  setDraggedCardIndex(index: number) {
    this.boardComp.setDraggedCardIndex(index);
  }

  setDragoverCardIndex(index: number) {
    this.boardComp.setDragoverCardIndex(index);
  }

  onDragOver(ev: Event) {
    ev.preventDefault(); // to allow dropping on this element
    this.boardComp.setDragoverCategoryIndex(this.category.index);
  }

  onDrop() {
    this.boardComp.onDrop();
  }

  editCategoryTitle() {
    this.editingTitle = true;
  }

  saveCategoryTitle() {
    this.editingTitle = false;
    if (this.titleInput && this.titleInput !== this.title) {
      this.categoryServ.updateCategory(this.category.id, this.titleInput).subscribe(
        resp => {
          if (resp.code === 0) {
            this.title = this.titleInput;
            this.category.title = this.titleInput;
          } else if (resp.code === -1) {
            alert('Error updating category title');
          } else {
            alert('Error: Unknown response');
          }
        }
      );
    }
  }

  deleteCategory() {
    this.boardComp.deleteCategory(this.category.id, this.category.index);
  }

  compareCard( a: Card, b: Card ) {
    if ( a.index < b.index ) {
      return -1;
    }
    if ( a.index > b.index ) {
      return 1;
    }
    return 0;
  }

}
