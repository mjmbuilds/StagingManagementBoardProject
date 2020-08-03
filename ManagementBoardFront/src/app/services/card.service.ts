import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';
import { Observable } from 'rxjs';
import { Card } from '../models/Card';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  constructor(private http: HttpClient, private urlServ: UrlService) { }

  addCard( title: string, description: string, owningCategoryId: string ): Observable<string> {
    const url = this.urlServ.getUrl() + 'card/add';
    const body = { title, description, owningCategoryId };
    return this.http.post<string>(url, body);
  }

  getCard( id: string ): Observable<Card> {
    const url: string = this.urlServ.getUrl() + 'card';
    const body = { id };
    return this.http.post<Card>(url, body);
  }

  updateCard( id: string, title: string, description: string ): Observable<string> {
    const url = this.urlServ.getUrl() + 'card/update';
    const body = { id, title, description };
    return this.http.put<string>(url, body);
  }

  deleteCard( id: string ): Observable<string> {
    const url = this.urlServ.getUrl() + 'card/remove/' + id;
    return this.http.delete<string>(url);
  }
}
