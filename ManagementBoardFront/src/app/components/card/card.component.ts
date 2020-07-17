import { Component, OnInit, Input } from '@angular/core';
import { Card } from 'src/app/models/Card';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() card: Card;
  title = 'Card';
  description = 'No description';

  constructor() { }

  ngOnInit(): void {
    if (this.card) {
      this.title = this.card.title;
      this.description = this.card.description;
    }
  }

}
