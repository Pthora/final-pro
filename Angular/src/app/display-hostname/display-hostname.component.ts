import { Component, OnInit } from '@angular/core';
import { DisplayHostnameService } from './display-hostname.service';
import { Memory } from '../memory/memory';
import { HomeService } from '../home/home.service';
import { HistoricService } from '../historic/historic.service';

@Component({
  selector: 'app-display-hostname',
  templateUrl: './display-hostname.component.html',
  styleUrls: ['./display-hostname.component.css']
})
export class DisplayHostnameComponent implements OnInit {
  hostname: string[] = new Array();
  hostnameNew: string;
  hostSelected: string;
  historicHost : string;
  selectedName: string;
  memory: Memory[];

  constructor(private displayService: DisplayHostnameService, private homeService: HomeService, private historicservice: HistoricService) { }

  ngOnInit() {
    this.displayService.currentMessage.subscribe(message => this.hostnameNew = message)
    this.historicservice.currentHostname.subscribe(message => this.historicHost = message)

    if (this.hostnameNew == "null" && this.historicHost == "nullHistoric") {//user comes directly from home page
      //the options from the historic time component have not been chosen yet
      this.homeService.currentMessage.subscribe(message => this.hostSelected = message)
      //therefore, the hostname is hostname given from home page 
    } else if (this.hostnameNew!="null" && this.historicHost=="nullHistoric") {//user has selected something in this page
      //this.hostSelected = this.selectedName
      
      this.displayService.currentMessage.subscribe(message => this.hostSelected = message);
      //this.historicservice.changeMessage("null")
    } else if(this.historicHost!="nullHistoric"){//user selects something from historic time and the hostname value gets passed here also
      this.historicservice.currentHostname.subscribe(message => this.hostSelected = message)
    }else{
      this.displayService.currentMessage.subscribe(message => this.hostSelected = message);
    }

    console.log("name of host display service- " + this.hostSelected)
    this.displayService.changeMessage(this.hostSelected) //pass it on to the service

    this.getAll();

  };

  myClickFunction($event) {
    this.selectedName = $event.target.options[$event.target.options.selectedIndex].text;
    console.log("display nav" + this.selectedName)
    this.hostSelected = this.selectedName
    console.log("CALL ME ALSO")
    this.displayService.changeMessage(this.hostSelected)
  }

  public getAll() {
    this.displayService.getHostname()
      .subscribe(data => {
        this.memory = data;
        let j = 0;
        for (let i = 0; i < this.memory.length; ++i) {
          if (!this.hostname.includes(this.memory[i]['hostname'])) {
            this.hostname[j] = this.memory[i]['hostname'];
            ++j;
          }

        }
      //  console.log("display hostname" + this.hostname);
      });
  }
}
