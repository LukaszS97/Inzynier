import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Raport } from '../models/raport';

@Component({
  selector: 'app-show-raports',
  templateUrl: './show-raports.component.html',
  styleUrls: ['./show-raports.component.css']
})
export class ShowRaportsComponent implements OnInit {

  raportList: Array<Raport>;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getAllRaport().then((result) => {
      this.raportList = result;
      console.log(this.raportList);
    });
  }

}
