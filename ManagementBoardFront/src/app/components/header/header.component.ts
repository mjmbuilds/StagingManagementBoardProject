import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';
import { IUser } from 'src/app/models/iUser';
import { AppComponent } from 'src/app/app.component';
import { Board } from 'src/app/models/Board';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loggedInUser: IUser;
  username: string;
  password: string;
  logedIn = false;
  logedInUserName = null;

  constructor(private appComponent: AppComponent, private userService: UserService) { }

  ngOnInit(): void {
  }

  login(): void {
    this.userService.login(this.username, this.password).subscribe(
      resp => {
        if (resp) {
          this.loggedInUser = resp;
          this.logedInUserName = resp.firstName;
          this.logedIn = true;
          this.username = null;
          this.password = null;
          if (resp.boards.length >= 1) {
            this.appComponent.openBoard(resp.boards[0]);
          }
        } else {
          alert('Incorrect Username or Password');
        }
      }
    );
  }

  logout(): void {
    this.loggedInUser = null;
    this.logedInUserName = null;
    this.logedIn = false;
    this.appComponent.closeBoard();
  }

  openSignup() {
    this.appComponent.openSignup();
  }

  closeSignup() {
    this.appComponent.closeSignup();
  }

}
