import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { UrlService } from './url.service';
import { IUser } from './models/iUser';
import { User } from './models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private loggedInUser: User = null;

  constructor(private http: HttpClient, private urlService: UrlService) { }

  get loggedUser() {
    return this.loggedInUser;
  }

  set loggedUser(user: User) {
    this.loggedInUser = user;
  }

  get isLoggedIn() {
    return this.loggedInUser ? true : false;
  }

  login(username: string, password: string): Observable<IUser> {
    const url: string = this.urlService.getUrl() + 'user/login';
    const body = { username: `${username}`, password: `${password}`};
    return this.http.post<IUser>(url, body);
  }

  signup(firstName: string, lastName: string, username: string, password: string): void {
    const url: string = this.urlService.getUrl() + 'user';
    const body = { firstName: `${firstName}`, lastName: `${lastName}`, username: `${username}`, password: `${password}`};
    this.http.post<IUser>(url, body);
  }

}
