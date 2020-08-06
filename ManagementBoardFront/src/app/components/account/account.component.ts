import { Component, OnInit } from '@angular/core';
import { AuthSimpleService } from 'src/app/services/auth-simple.service';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  accountId: string;

  accountFirstnameOriginal: string;
  accountLastnameOriginal: string;
  accountUsernameOriginal: string;

  accountFirstname: string;
  accountLastname: string;
  accountUsername: string;
  newPassword: string;
  confNewPassword: string;

  constructor(private userSer: UserService, private authServ: AuthSimpleService, private router: Router,) { }

  ngOnInit(): void {
    if (this.authServ.hasLoggedInUser) {
      this.accountId = this.authServ.loggedInUser.id;
      this.accountFirstnameOriginal = this.authServ.loggedInUser.firstName;
      this.accountLastnameOriginal = this.authServ.loggedInUser.lastName;
      this.accountUsernameOriginal = this.authServ.loggedInUser.username;
      this.accountFirstname = this.accountFirstnameOriginal;
      this.accountLastname = this.accountLastnameOriginal;
      this.accountUsername = this.accountUsernameOriginal;
      this.newPassword = null;
      this.confNewPassword = null;
    } else {
      this.router.navigateByUrl(''); // if not logged in, navigate to home page
    }
  }

  canSubmitUpdate(): boolean {
    if (this.accountFirstname
      && this.accountLastname
      && this.accountUsername
      && (
        this.accountFirstname !== this.accountFirstnameOriginal
        || this.accountLastname !== this.accountLastnameOriginal
        || this.accountUsername !== this.accountUsernameOriginal)) {
      return true;
    }
    return false;
  }

  canSubmitPass(): boolean {
    if (this.newPassword
      && this.confNewPassword
      && this.confNewPassword === this.newPassword) {
      return true;
    }
    return false;
  }

  submitUpdate() {
    this.userSer.updateUserInfo(
      this.accountId,
      this.accountFirstname,
      this.accountLastname,
      this.accountUsername ).subscribe(
        resp => {
          if (resp.code === 0) {
            this.authServ.loggedInUser.firstName = this.accountFirstname;
            this.authServ.loggedInUser.lastName = this.accountLastname;
            this.authServ.loggedInUser.username = this.accountUsername;
            alert('Account info updated successfully');
            this.ngOnInit();
          } else if (resp.code === -1) {
            alert('Unable to update account info');
          } else {
            alert('Error: Unknown response');
          }
        }
      );
  }

  reset() {
    this.accountFirstname = this.accountFirstnameOriginal;
    this.accountLastname = this.accountLastnameOriginal;
    this.accountUsername = this.accountUsernameOriginal;
  }

  changePassword() {
    this.userSer.updatePassword(
      this.accountId,
      this.newPassword).subscribe(
        resp => {
          if (resp.code === 0) {
            alert('Password updated successfully');
            this.authServ.setPassword(this.newPassword);
            this.ngOnInit();
          } else if (resp.code === -1) {
            alert('Unable to update password');
          } else {
            alert('Error: Unknown response');
          }
        }
    );
  }

  deleteAccount() {
    if (confirm('Do you really want to delete your account?')) {
      this.userSer.closeAcct(this.accountId).subscribe(
        resp => {
          if (resp.code === 0) {
            alert('Account deleted successfully');
            this.authServ.clearSession();
            window.location.href = '/';
          } else if (resp.code === -1) {
            alert('Error attempting to delete account');
          } else {
            alert('Error: Unknown response');
          }
        }
      );
    }
  }

}
