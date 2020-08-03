import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Board } from '../models/Board';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  constructor(private http: HttpClient, private urlServ: UrlService) { }

  addBoard( title: string, owningUserId: string ): Observable<string> {
    const url = this.urlServ.getUrl() + 'board/add';
    const body = { title, owningUserId };
    return this.http.post<string>(url, body);
  }

  getBoard( id: string ): Observable<Board> {
    const url: string = this.urlServ.getUrl() + 'board';
    const body = { id };
    return this.http.post<Board>(url, body);
  }

  updateBoard( id: string, title: string ): Observable<string> {
    const url = this.urlServ.getUrl() + 'board/update';
    const body = { id, title };
    return this.http.put<string>(url, body);
  }

  deleteBoard( id: string ): Observable<string> {
    const url = this.urlServ.getUrl() + 'board/remove/' + id;
    return this.http.delete<string>(url);
  }
}
