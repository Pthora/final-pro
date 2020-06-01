import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Historic } from './historic';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class HistoricService {
  headers: HttpHeaders | { 'Content-Type': 'application/json' };

  constructor(private http: HttpClient) { }
  hostname: string;
  private messageSourceStart = new BehaviorSubject<string>("defaultDateStart");//behaviour subject to pass from-timestamp
  StartDate = this.messageSourceStart.asObservable();
  private messageSourceEnd = new BehaviorSubject<string>("defaultDateEnd");//behaviour subject to pass to-timestamp 
  EndDate = this.messageSourceEnd.asObservable();

  private messageSource = new BehaviorSubject<string>("nullHistoric"); //behaviour subject for hostname
  currentHostname = this.messageSource.asObservable();

  /*putHostname(name) {
    return this.hostname = name;
  }*/

  changeMessageStart(message: string) {//for keeping track of from timestamp
    this.messageSourceStart.next(message);
  }

  changeMessageEnd(message: string) {//for keeping track of to timestamp
    this.messageSourceEnd.next(message);
  }

  changeMessage(message : string){ //for keeping track of hostname
    this.messageSource.next(message);
  }

}