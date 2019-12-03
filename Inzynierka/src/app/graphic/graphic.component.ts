import { Component, OnInit } from '@angular/core';
import { Graphic } from '../models/graphic';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-graphic',
  templateUrl: './graphic.component.html',
  styleUrls: ['./graphic.component.css']
})
export class GraphicComponent implements OnInit {

  grafik: Array<Graphic>;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getGraphic().then((graphic) => {
      this.grafik = graphic;
    });
  }


}
