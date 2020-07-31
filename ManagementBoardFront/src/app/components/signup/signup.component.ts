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
  signupConfirmPassword: string;

  constructor(private authServ: AuthSimpleService) { }

  ngOnInit(): void {
  }

  canSubmit(): boolean {
    if (this.signupFirstname
    && this.signupLastname
    && this.signupUsername
    && this.signupPassword
    && this.signupConfirmPassword
    && this.signupPassword === this.signupConfirmPassword) {
      return true;
    }
    return false;
  }

  closeSignup() {
    location.href = '';
  }

  submitSignup() {
    if (this.canSubmit()) {
      this.authServ.signup(
        this.signupFirstname,
        this.signupLastname,
        this.signupUsername,
        this.signupPassword
        ).subscribe();
      this.closeSignup();
    }
  }

}
