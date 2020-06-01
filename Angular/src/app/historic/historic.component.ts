import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HistoricService } from './historic.service'
import { Historic } from './historic';
import { Subscription, timer } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { HomeService } from '../home/home.service';
import { DisplayHostnameService } from '../display-hostname/display-hostname.service';
import { Memory } from '../memory/memory';
import { log } from 'util';
@Component({
  selector: 'app-historic',
  templateUrl: './historic.component.html',
  styleUrls: ['./historic.component.css']
})
export class HistoricComponent implements OnInit {
  historic: Historic = {};
  data: string[] = new Array();
  hostname: string;
  hostnameList: string[] = new Array();
  hostnameNew: string;
  subscription: Subscription;
  selectedName: string;
  memory: Memory[];
  selectedTimestamp: Date;
  selectedTimestamp2: Date;
  selectedPreset: string;
  newDate: Date = new Date();


  constructor(private router: Router, private historicService: HistoricService, private homeService: HomeService, private displayService: DisplayHostnameService) { }

  ngOnInit() {

    this.displayService.currentMessage.subscribe(message => this.hostnameNew = message)

    if (this.hostnameNew == "null" && this.selectedName == undefined) {//user comes directly from home page
      //the options from the display host component in real time component have not been chosen yet
      this.homeService.currentMessage.subscribe(message => this.hostname = message)
     // this.historicService.changeMessage(this.hostname) //pass it on to the service

      //therefore, the hostname is hostname given from home page
    } else if (this.selectedName != undefined) {//user selects something in this page
      this.hostname = this.selectedName
     // this.historicService.changeMessage(this.hostname) //pass it on to the service
      this.displayService.changeMessage("null")
    } else {//user selects something from real time and the hostname value gets passed here also
      this.displayService.currentMessage.subscribe(message => this.hostname = message)
     
      
    }

    console.log("name of host historic- " + this.hostname)
    this.historicService.changeMessage(this.hostname) //pass it on to the service

    this.getAll();


  }

  myClickFunction($event) {
    this.selectedName = $event.target.options[$event.target.options.selectedIndex].text;
    console.log("display nav" + this.selectedName)
    this.hostname = this.selectedName;
    this.historicService.changeMessage(this.hostname)
  }

  myClickFunction2($event) {
    this.selectedTimestamp = $event.target.value;
    console.log(this.selectedTimestamp);
    this.data[0] = this.selectedTimestamp.toString().concat(":00");
    this.historic.fromTimestamp = this.data[0].replace('T', '_');
    this.historicService.changeMessageStart(this.historic.fromTimestamp)
    console.log("from timestamp" + this.historic.fromTimestamp)
  }

  myClickFunction4($event) {
    this.selectedTimestamp2 = $event.target.value;
    console.log(this.selectedTimestamp2);
    this.data[1] = this.selectedTimestamp2.toString().concat(":00");
    this.historic.toTimestamp = this.data[1].toString().replace('T', '_');
    this.historicService.changeMessageEnd(this.historic.toTimestamp)
    console.log("to timestamp" + this.historic.toTimestamp)
  }


  myClickFunction3($event) {
    this.selectedPreset = $event.target.options[$event.target.options.selectedIndex].text;
    console.log("Preset " + this.selectedPreset)

  }



  onView() {
    //console.log(this.historic);
    console.log("onview hostname"+this.hostname)
  /*  console.log(this.selectedTimestamp);
     console.log(this.selectedTimestamp2);
     //console.log(this.selectedPreset);
     this.data[0]=this.selectedTimestamp.toString().concat(":00");
     this.data[1]=this.selectedTimestamp2.toString().concat(":00");
     this.historic.fromTimestamp=this.data[0].replace('T', '_');
     this.historic.toTimestamp=this.data[1].toString().replace('T','_');
     console.log(JSON.stringify(this.historic)); 
     //console.log(this.data)
     this.historicService.changeMessage(this.historic.fromTimestamp)
     this.historicService.changeMessage1(this.historic.toTimestamp)*/
  }
  

  public getAll() {
    this.displayService.getHostname()
      .subscribe(data => {
        this.memory = data;
        let j = 0;
        for (let i = 0; i < this.memory.length; ++i) {

          if (!this.hostnameList.includes(this.memory[i]['hostname'])) {
            this.hostnameList[j] = this.memory[i]['hostname'];
            ++j;
          }

        }
        // console.log("display hostname"+this.hostname);
      });
  }

}