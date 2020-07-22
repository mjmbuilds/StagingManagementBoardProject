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

  /*
  login(username: string, password: string): Observable<IUser> {
    const url: string = this.urlService.getUrl() + 'user/login';
    const body = { username, password };
    return this.http.post<IUser>(url, body);
  }
  */

  //TODO convert to using Observable<HttpResponse<User>>
  login(username: string, password: string): Observable<IUser> {
    const url: string = this.urlService.getUrl() + 'user/login';
    const body = { username, password };
    return this.http.post<IUser>(url, body);
  }

  signup(firstName: string, lastName: string, username: string, password: string): Observable<IUser> {
    const url: string = this.urlService.getUrl() + 'user/signup';
    const body = { firstName: `${firstName}`, lastName: `${lastName}`, username: `${username}`, password: `${password}`};

    console.log('signing up');

    return this.http.post<IUser>(url, body);
  }

}
