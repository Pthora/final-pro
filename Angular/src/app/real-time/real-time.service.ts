import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Power } from '../power/power';
import { Memory } from '../memory/memory';
import { Disk } from '../disk/disk';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable()
export class RealTimeService {
  constructor(private http: HttpClient) { }

  private powerUrl = '/apiPower';
  private memUrl = '/apiMemory';
  private diskUrl = '/apiDisk';
  private postHostUrlPower = '';
  private postHostUrlDisk = '';
  private postHostUrlMem = '';

  hostname : String;

  public getHostInfoPower() {
    this.postHostUrlPower = this.powerUrl+"/"+this.hostname;
    console.log("url"+this.postHostUrlPower)
    return this.http.post<Power>(this.postHostUrlPower,this.hostname);
    
  }

  public getHostInfoMem() {
    this.postHostUrlMem = this.memUrl+"/"+this.hostname;
    console.log("url"+this.postHostUrlMem)
    return this.http.post<Power>(this.postHostUrlMem,this.hostname);
    
  }

  public getHostInfoDisk() {
    this.postHostUrlDisk = this.diskUrl+"/"+this.hostname;
    console.log("url"+this.postHostUrlDisk)
    return this.http.post<Power>(this.postHostUrlDisk,this.hostname);
    
  }

  public getPowerDetails(): Observable<Power[]> {
    this.getHostInfoPower()
    return this.http.get<Power[]>(this.postHostUrlPower);
  }

  public getDiskDetails(): Observable<Disk[]> {
    this.getHostInfoDisk()
    return this.http.get<Disk[]>(this.postHostUrlDisk);
  }

  public getMemDetails(): Observable<Memory[]> {
    this.getHostInfoMem()
    return this.http.get<Memory[]>(this.postHostUrlMem);
  }

  putHostname(name){
   return this.hostname = name;
  }

}