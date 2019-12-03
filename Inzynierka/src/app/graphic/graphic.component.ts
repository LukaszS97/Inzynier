import { Component, OnInit } from '@angular/core';
import { Graphic } from '../models/graphic';

@Component({
  selector: 'app-graphic',
  templateUrl: './graphic.component.html',
  styleUrls: ['./graphic.component.css']
})
export class GraphicComponent implements OnInit {

  tablica: Array<Graphic>;

  constructor() { }

  ngOnInit() {
  }

}
