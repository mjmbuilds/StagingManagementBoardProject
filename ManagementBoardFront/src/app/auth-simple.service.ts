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

  get isLoggedIn() {
    return this.loggedUser ? true : false;
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
