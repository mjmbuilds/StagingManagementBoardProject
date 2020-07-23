import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { HttpClient} from '@angular/common/http';
import { UrlService } from './url.service';
import { IUser } from './models/iUser';
import { User } from './models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthSimpleService {

  private loggedUser: User = null;

  constructor(private http: HttpClient, private urlService: UrlService) { }

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
  login(username: string, password: string): Observable<IUser> {
    const url: string = this.urlService.getUrl() + 'user/login';
    const body = { username, password };
    return this.http.post<IUser>(url, body);
  }

  signup(firstName: string, lastName: string, username: string, password: string): Observable<IUser> {
    const url: string = this.urlService.getUrl() + 'user/signup';
    const body = { firstName, lastName, username, password};
    return this.http.post<IUser>(url, body);
  }

}
