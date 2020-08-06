import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Board } from '../models/Board';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';
import { CodeMessage } from '../models/CodeMessage';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  constructor(private http: HttpClient, private urlServ: UrlService) { }

  addBoard( title: string, owningUserId: string ): Observable<CodeMessage> {
    const url = this.urlServ.getUrl() + 'board/add';
    const body = { title, owningUserId };
    return this.http.post<CodeMessage>(url, body);
  }

  getBoard( id: string ): Observable<Board> {
    const url: string = this.urlServ.getUrl() + 'board/' + id;
    return this.http.get<Board>(url);
  }

  updateBoard( id: string, title: string ): Observable<CodeMessage> {
    const url = this.urlServ.getUrl() + 'board/update';
    const body = { id, title };
    return this.http.put<CodeMessage>(url, body);
  }

  deleteBoard( id: string ): Observable<CodeMessage> {
    const url = this.urlServ.getUrl() + 'board/remove/' + id;
    return this.http.delete<CodeMessage>(url);
  }
}
