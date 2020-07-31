import { Injectable } from '@angular/core';
import { AuthSimpleService } from './auth-simple.service';
import { UrlService } from './url.service';
import { HttpClient } from '@angular/common/http';
import { IUser } from '../models/iUser';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private urlService: UrlService, private authService: AuthSimpleService) { }

  signup(firstName: string, lastName: string, username: string, password: string): Observable<IUser> {
    const url: string = this.urlService.getUrl() + 'user/signup';
    const body = { firstName, lastName, username, password };
    return this.http.post<IUser>(url, body);
  }

  updateUserInfo( id: string, firstName: string, lastName: string, username: string): Observable<string> {
    const url: string = this.urlService.getUrl() + 'user/update';
    const body = { id, firstName, lastName, username };
    return this.http.put<string>(url, body);
  }

  updatePassword( id: string, password: string): Observable<string> {
    const url: string = this.urlService.getUrl() + 'user/update';
    const body = { id, password };
    return this.http.put<string>(url, body);
  }

  closeAcct( id: string ): Observable<string> {
    const url: string = this.urlService.getUrl() + 'user/remove/' + id;
    return this.http.delete<string>(url);
  }

}
