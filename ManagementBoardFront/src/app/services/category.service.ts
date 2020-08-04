import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';
import { Observable } from 'rxjs';
import { Category } from '../models/Category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient, private urlServ: UrlService) { }

  addCategory( title: string, owningBoardId: string ): Observable<string> {
    const url = this.urlServ.getUrl() + 'category/add';
    const body = { title, owningBoardId };
    return this.http.post<string>(url, body);
  }

  getCategory( id: string ): Observable<Category> {
    const url: string = this.urlServ.getUrl() + 'category/' + id;
    return this.http.get<Category>(url);
  }

  updateCategory( id: string, title: string ): Observable<string> {
    const url = this.urlServ.getUrl() + 'category/update';
    const body = { id, title };
    return this.http.put<string>(url, body);
  }

  deleteCategory( id: string ): Observable<string> {
    const url = this.urlServ.getUrl() + 'category/remove/' + id;
    return this.http.delete<string>(url);
  }
}
