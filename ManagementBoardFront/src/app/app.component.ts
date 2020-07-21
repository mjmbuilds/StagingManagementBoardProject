import { Component, ViewChild } from '@angular/core';
import { Board } from './models/Board';
import { BoardComponent } from './components/board/board.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Management Board';

  @ViewChild(BoardComponent) boardComp: BoardComponent;

  constructor() {  }

}
