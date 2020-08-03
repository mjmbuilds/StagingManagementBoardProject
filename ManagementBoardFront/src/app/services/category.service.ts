import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient, private urlService: UrlService) { }

  addCategory() {
    //TODO
  }

  getCategory() {
    //TODO
  }

  updateCategory() {
    //TODO
  }

  deleteCategory() {
    //TODO
  }
}
