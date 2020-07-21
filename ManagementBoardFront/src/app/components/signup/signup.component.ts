import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';

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

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  closeSignup() {
    location.href = '';
  }

  submitSignup() {
    if (this.signupFirstname && this.signupLastname && this.signupUsername && this.signupPassword) {
      this.userService.signup(this.signupFirstname, this.signupLastname, this.signupUsername, this.signupPassword);
      this.closeSignup();
    }
  }

}
