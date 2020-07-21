import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';
import { IUser } from 'src/app/models/iUser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loggedInUser: IUser;
  username: string;
  password: string;
  logedInUserName = null;

  get logedIn() {
    return this.loggedInUser ? true : false;
  }

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
  }

  login(): void {
    this.userService.login(this.username, this.password).subscribe(
      resp => {
        if (resp) {
          this.loggedInUser = resp;
          this.userService.loggedUser = resp;
          this.logedInUserName = resp.firstName;
          this.username = null;
          this.password = null;
          this.router.navigateByUrl('board');
        } else {
          alert('Incorrect Username or Password');
        }
      }
    );
  }

  logout(): void {
    this.userService.loggedUser = null;
    this.loggedInUser = null;
    this.logedInUserName = null;
    this.router.navigateByUrl('');
  }

  openSignup() {
    location.href = '/signup';
  }

}
