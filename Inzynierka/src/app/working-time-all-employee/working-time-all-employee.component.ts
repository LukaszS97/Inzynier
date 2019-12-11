import { Component, OnInit } from '@angular/core';
import { WorkedHours } from '../models/worked-hours';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-working-time-all-employee',
  templateUrl: './working-time-all-employee.component.html',
  styleUrls: ['./working-time-all-employee.component.css']
})
export class WorkingTimeAllEmployeeComponent implements OnInit {

  sendRequestHours: WorkedHours = new WorkedHours();
  workedHour;
  hours: Array<WorkedHours>;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }
  askForHours() {
    this.workedHour = this.sendRequestHours.month + '/' + this.sendRequestHours.year;
    this.httpService.getHoursAllEmployee(this.workedHour).then((result) => {
      this.hours = result;
    });
  }
}
