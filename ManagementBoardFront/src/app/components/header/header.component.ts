import { Component, OnInit } from '@angular/core';
import { IUser } from 'src/app/models/iUser';
import { Router } from '@angular/router';
import { AuthSimpleService } from 'src/app/auth-simple.service';
import { User } from 'src/app/models/User';

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

  constructor(private router: Router, private authServ: AuthSimpleService) { }

  ngOnInit(): void {
    /*
    // Was going to check if logged in, but authServ loses data on reload
    // will need to persist data of some sort when logged in
    if (this.authServ.isLoggedIn) {
      this.username = this.authServ.loggedInUser.username;
      this.password = this.authServ.loggedInUser.password;
      this.login();
    }
    */
  }

  login(): void {
    this.authServ.login(this.username, this.password).subscribe(
      resp => {
        if (resp) {
          this.loggedInUser = resp;
          this.authServ.loggedInUser = resp;
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
    this.authServ.loggedInUser = null;
    this.loggedInUser = null;
    this.logedInUserName = null;
    this.router.navigateByUrl('');
  }

  signup() {
    location.href = '/signup';
  }

}
