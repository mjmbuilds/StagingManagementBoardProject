import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';
import { Observable } from 'rxjs';
import { Card } from '../models/Card';
import { CodeMessage } from '../models/CodeMessage';
import { IndexList } from '../models/indexList';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  constructor(private http: HttpClient, private urlServ: UrlService) { }

  updateCardIndexList( indexList: IndexList): Observable<CodeMessage> {
    const idList = indexList.idList;
    const url = this.urlServ.getUrl() + 'card/update-index-list';
    const body = { idList };
    return this.http.put<CodeMessage>(url, body);
  }

  updateCardCategory( id: string, owningCategoryId: string ): Observable<CodeMessage> {
    const url = this.urlServ.getUrl() + 'card/update';
    const body = { id, owningCategoryId };
    return this.http.put<CodeMessage>(url, body);
  }

  addCard( title: string, description: string, owningCategoryId: string, index: number ): Observable<CodeMessage> {
    const url = this.urlServ.getUrl() + 'card/add';
    const body = { title, description, owningCategoryId, index };
    return this.http.post<CodeMessage>(url, body);
  }

  getCard( id: string ): Observable<Card> {
    const url: string = this.urlServ.getUrl() + 'card/' + id;
    return this.http.get<Card>(url);
  }

  updateCard( id: string, title: string, description: string ): Observable<CodeMessage> {
    const url = this.urlServ.getUrl() + 'card/update';
    const body = { id, title, description };
    return this.http.put<CodeMessage>(url, body);
  }

  deleteCard( id: string ): Observable<CodeMessage> {
    const url = this.urlServ.getUrl() + 'card/remove/' + id;
    return this.http.delete<CodeMessage>(url);
  }
}
