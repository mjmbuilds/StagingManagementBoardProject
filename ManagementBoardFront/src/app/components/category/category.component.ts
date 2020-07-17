import { Component, OnInit, Input } from '@angular/core';
import { Category } from 'src/app/models/Category';
import { Card } from 'src/app/models/Card';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  @Input() category: Category;
  title = 'Category';
  cards: Card[];

  constructor() {  }

  ngOnInit(): void {
    if (this.category) {
      this.title = this.category.title;
      this.cards = this.category.cards;
    }
  }

}
