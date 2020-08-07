import { Component, OnInit, Input, Host } from '@angular/core';
import { Card } from 'src/app/models/Card';
import { CategoryComponent } from '../category/category.component';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() card: Card;
  title = 'Card';
  description = 'No description';

  constructor(@Host() private categoryComp: CategoryComponent) { }

  ngOnInit(): void {
    if (this.card) {
      this.title = this.card.title;
      this.description = this.card.description;
    }
  }

  dragCardStart(ev: Event) {
    this.categoryComp.setDraggedCardIndex(this.card.index);
  }

  onDragOver(ev: Event) {
    ev.preventDefault(); // to allow dropping on this element
    this.categoryComp.setDragoverCardIndex(this.card.index);
  }

}
