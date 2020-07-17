import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { UrlService } from './url.service';
import { IUser } from './models/iUser';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private urlService: UrlService) { }

  login(username: string, password: string): Observable<IUser> {
    const url: string = this.urlService.getUrl() + 'user/login';
    const body = { username: `${username}`, password: `${password}`};
    return this.http.post<IUser>(url, body);
  }
}
