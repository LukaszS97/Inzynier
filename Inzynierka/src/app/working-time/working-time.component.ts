import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { WorkedHours } from '../models/worked-hours';

@Component({
  selector: 'app-working-time',
  templateUrl: './working-time.component.html',
  styleUrls: ['./working-time.component.css']
})
export class WorkingTimeComponent implements OnInit {
  hours: Array<WorkedHours>;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }

}
