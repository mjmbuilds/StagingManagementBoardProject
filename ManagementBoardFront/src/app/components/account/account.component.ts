import { Component, OnInit } from '@angular/core';
import { AuthSimpleService } from 'src/app/auth-simple.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  accountFirstnameOriginal: string;
  accountLastnameOriginal: string;
  accountUsernameOriginal: string;

  accountFirstname: string;
  accountLastname: string;
  accountUsername: string;

  constructor(private authServ: AuthSimpleService) { }

  ngOnInit(): void {
    this.accountFirstnameOriginal = this.authServ.loggedInUser.firstName;
    this.accountLastnameOriginal = this.authServ.loggedInUser.lastName;
    this.accountUsernameOriginal = this.authServ.loggedInUser.username;
    this.accountFirstname = this.accountFirstnameOriginal;
    this.accountLastname = this.accountLastnameOriginal;
    this.accountUsername = this.accountUsernameOriginal;
  }

  submitUpdate() {
    //TODO
  }

  reset() {
    this.accountFirstname = this.accountFirstnameOriginal;
    this.accountLastname = this.accountLastnameOriginal;
    this.accountUsername = this.accountUsernameOriginal;
  }

}
