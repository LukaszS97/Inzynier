import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { WorkedHours } from '../models/worked-hours';

@Component({
  selector: 'app-worked-hours',
  templateUrl: './worked-hours.component.html',
  styleUrls: ['./worked-hours.component.css']
})
export class WorkedHoursComponent implements OnInit {
  sendRequestHours: WorkedHours = new WorkedHours();
  hidden: boolean;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }
  sumHours() {
    this.hidden = true;
    console.log(this.sendRequestHours);
    this.httpService.sumHours(this.sendRequestHours);
  }
}
