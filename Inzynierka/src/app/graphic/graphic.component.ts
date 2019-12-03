import { Component, OnInit } from '@angular/core';
import { WorkSchedule } from '../models/work-schedule';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-graphic',
  templateUrl: './graphic.component.html',
  styleUrls: ['./graphic.component.css']
})
export class GraphicComponent implements OnInit {

  grafik: Array<WorkSchedule>;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getGraphic().then((graphic) => {
      this.grafik = graphic;
      console.log(this.grafik);
    });
  }


}
