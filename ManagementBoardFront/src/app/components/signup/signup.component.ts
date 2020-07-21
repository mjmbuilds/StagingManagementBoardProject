import { Component, OnInit } from '@angular/core';
import { AuthSimpleService } from 'src/app/auth-simple.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupFirstname: string;
  signupLastname: string;
  signupUsername: string;
  signupPassword: string;

  constructor(private authServ: AuthSimpleService) { }

  ngOnInit(): void {
  }

  closeSignup() {
    location.href = '';
  }

  submitSignup() {
    if (this.signupFirstname && this.signupLastname && this.signupUsername && this.signupPassword) {
      this.authServ.signup(this.signupFirstname, this.signupLastname, this.signupUsername, this.signupPassword);
      this.closeSignup();
    }
  }

}
