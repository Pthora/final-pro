import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Memory } from '../memory/memory'
import {Power} from '../Power/power'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ChartDisplayService {

  constructor(private http: HttpClient) { }
  private powerUrl = '/apiChartPower'
  private memUrl = '/apiChartMemory';
  private postHostUrl = '';
  private postPowerHostUrl = '';
  hostname : String = "";
  public point:Object;
  startTimestamp:String;
  endTimestamp:String;

 /* public getMemoryHostnames() : Observable<Memory[]> {
    return this.http.get<Memory[]>(this.memUrl);
  }
*/
  public getHostInfo() {
    this.postHostUrl = this.memUrl+"/"+this.hostname+"/"+this.startTimestamp+"/"+this.endTimestamp;
    console.log("url"+this.postHostUrl)
    return this.http.post<Memory>(this.postHostUrl,this.hostname);
  }

  public getPowerHostInfo() {
    this.postPowerHostUrl = this.powerUrl+"/"+this.hostname+"/"+this.startTimestamp+"/"+this.endTimestamp;
    console.log("url"+this.postPowerHostUrl)
    return this.http.post<Power>(this.postPowerHostUrl,this.hostname);
  }

  public getMemoryDetails(): Observable<Memory[]> {
    this.getHostInfo()
    return this.http.get<Memory[]>(this.postHostUrl);
  }

  public getPowerDetails(): Observable<Power[]> {
    this.getPowerHostInfo()
    return this.http.get<Power[]>(this.postPowerHostUrl);
  }

  putHostname(name){
   return this.hostname = name;
  }

  putTimestamps(start,end){
      this.startTimestamp = start;
      this.endTimestamp = end;
  }

}