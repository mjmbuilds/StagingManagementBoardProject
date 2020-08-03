import { Component, OnInit } from '@angular/core';
import { AuthSimpleService } from 'src/app/services/auth-simple.service';
import { UserService } from 'src/app/services/user.service';

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

  constructor(private userServ: UserService, private authServ: AuthSimpleService) { }

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
      this.userServ.signup(
        this.signupFirstname,
        this.signupLastname,
        this.signupUsername,
        this.signupPassword
        ).subscribe(
          resp => {
            if (resp.toString() === '0') {
              alert('Successfully created account. Please log in.');
              this.closeSignup();
            } else if (resp.toString() === '-1') {
              alert('Failed to create account');
            }else {
              alert('Error: Unknown response');
            }
          }
        );
    }
  }

}
