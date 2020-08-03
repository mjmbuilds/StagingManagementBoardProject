import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { HttpClient} from '@angular/common/http';
import { UrlService } from './url.service';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthSimpleService {

  private loggedUser: User = null;

  constructor(private http: HttpClient, private urlServ: UrlService) { }

  get loggedInUser() {
    return this.loggedUser;
  }

  set loggedInUser(user: User) {
    this.loggedUser = user;
  }

  get hasLoggedInUser() {
    return this.loggedUser ? true : false;
  }

  get hasSession() {
    return sessionStorage.getItem('username') ? true : false;
  }

  setSession(user: User) {
    //TODO update to a proper session token instead of saving user/pass
    sessionStorage.setItem('username', user.username);
    sessionStorage.setItem('password', user.password);
    this.loggedUser = user;
  }
  getSessionUsername() {
    return sessionStorage.getItem('username');
  }
  getSessionPassword() {
    return sessionStorage.getItem('password');
  }
  clearSession() {
    sessionStorage.clear();
    this.loggedUser = null;
  }

  //TODO convert to using Observable<HttpResponse<User>>
  login(username: string, password: string): Observable<User> {
    const url: string = this.urlServ.getUrl() + 'user/login';
    const body = { username, password };
    return this.http.post<User>(url, body);
  }

}
