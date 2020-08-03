import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  constructor(private http: HttpClient, private urlService: UrlService) { }

  addCard() {
    //TODO
  }

  getCard() {
    //TODO
  }

  updateCard() {
    //TODO
  }

  deleteCard() {
    //TODO
  }
}
