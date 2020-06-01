import { Component, OnInit, Input } from '@angular/core';
import { HomeService } from '../home/home.service';
import { RealTimeService } from './real-time.service';
import { DisplayHostnameService } from '../display-hostname/display-hostname.service';
import { Subscription, timer } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Memory } from '../memory/memory';
import { Disk } from '../disk/disk';
import { Power } from '../power/power';


@Component({
  selector: 'app-real-time',
  templateUrl: './real-time.component.html',
  styleUrls: ['./real-time.component.css']
})
export class RealTimeComponent implements OnInit {
  message: string;
  hostname: string;
  hostnameNew: string;
  memory: Memory[];
  avgPower: number;
  cpuPercent : string;
  ramPercent: string;
  disk: Disk[];
  diskUsed: string;
  power: Power[];
  powerused: string;
  parameter: String[] = new Array();
  parameterCpu: String[] = new Array();

  constructor(private homeService: HomeService, private displayService: DisplayHostnameService, private realService: RealTimeService) { }

  ngOnInit() {
    this.displayService.currentMessage.subscribe(message => this.hostnameNew = message)

    if (this.hostnameNew == "null") { //the option from the real time component has not been chosen yet
      this.homeService.currentMessage.subscribe(message => this.hostname = message)//therefore, the hostname is hostname given from home page
    } else {

      this.displayService.currentMessage.subscribe(message => this.hostname = message)
    }
    console.log("name of host summary - " + this.hostname)
    this.realService.putHostname(this.hostname) //pass it on to the service
    this.getAll();
  }

  public getAll() {
    //get memory and cpu
     this.realService.getMemDetails().subscribe(data => {
      this.memory = data;
        this.parameter[0] = this.memory[0]['ramPercent'];
        this.parameterCpu[0] = this.memory[0]['cpuPercent'];
        this.memory[0]['ramPercent'] = this.parameter[0].replace('%', ''); //replace the percent sign so that comparision becomes possible
        this.cpuPercent = this.memory[0]['ramPercent']
        this.memory[0]['cpuPercent'] = this.parameterCpu[0].replace('%', ''); //replace the percent sign so that comparision becomes possible
        this.ramPercent=this.memory[0]['ramPercent']
        console.log("Memory " + JSON.stringify(this.memory));
    });

    //get power details
    this.realService.getPowerDetails().subscribe(data => {
      this.power = data;
      for (let i = 0; i < this.power.length; i++) {
        this.parameter[i] = this.power[i]['core0'];
        this.power[i]['core0'] = this.parameter[i].replace('°C', ''); //replace the degree sign so that comparision becomes possible
        this.avgPower = +this.power[i]['core0'];
    
        this.parameter[i] = this.power[i]['core1'];
        this.power[i]['core1'] = this.parameter[i].replace('°C', ''); //replace the degree sign so that comparision becomes possible
        this.avgPower = this.avgPower + parseFloat(this.power[i]['core1']);

        this.parameter[i] = this.power[i]['core2'];
        this.power[i]['core2'] = this.parameter[i].replace('°C', ''); //replace the degree sign so that comparision becomes possible
        this.avgPower = this.avgPower + parseFloat(this.power[i]['core2']);

        this.parameter[i] = this.power[i]['core3'];
        this.power[i]['core3'] = this.parameter[i].replace('°C', ''); //replace the degree sign so that comparision becomes possible
        this.avgPower = this.avgPower + parseFloat(this.power[i]['core3']);
      }
      this.avgPower = this.avgPower/4;
      console.log("Power " + JSON.stringify(this.power));
      console.log("avg power"+this.avgPower)
    });

    //get disk details
    this.realService.getDiskDetails().subscribe(data => {
      this.disk = data;
      this.parameter[0] = this.disk[0]['usedPercent'];
      this.diskUsed = this.parameter[0].replace('%', '');
      console.log("diskUsed" + this.diskUsed)
    });

  }

}
