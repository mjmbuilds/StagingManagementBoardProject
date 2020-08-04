import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthSimpleService } from 'src/app/services/auth-simple.service';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loggedInUser: User;
  username: string;
  password: string;
  logedInUserName = null;

  get logedIn() {
    return this.loggedInUser ? true : false;
  }

  constructor(private router: Router, private authServ: AuthSimpleService) { }

  ngOnInit(): void {
    // Check if there is a valid session
    if (this.authServ.hasSession) {
      //TODO adjust to use tokens
      this.username = this.authServ.getSessionUsername();
      this.password = this.authServ.getSessionPassword();
      this.login();
    } else {
      this.loggedInUser = null;
      this.logedInUserName = null;
      this.username = null;
      this.password = null;
    }
  }

  canSubmit() {
    if (this.username && this.password) {
      return true;
    }
    return false;
  }

  login(): void {
    this.authServ.login(this.username, this.password).subscribe(
      resp => {
        if (resp) {
          this.authServ.setSession(resp);
          this.loggedInUser = resp;
          this.logedInUserName = resp.firstName;
          this.router.navigateByUrl('board');
        } else {
          alert('Incorrect Username or Password');
        }
      }
    );
  }

  logout(): void {
    this.authServ.clearSession();
    this.loggedInUser = null;
    this.logedInUserName = null;
    this.username = null;
    this.password = null;
    this.router.navigateByUrl('');
  }

  signup() {
    location.href = '/signup';
  }

}
