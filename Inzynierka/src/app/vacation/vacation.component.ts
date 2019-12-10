import { Component, OnInit } from '@angular/core';
import { VacationForm } from '../models/vacation-form';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-vacation',
  templateUrl: './vacation.component.html',
  styleUrls: ['./vacation.component.css']
})
export class VacationComponent implements OnInit {

  vacation: VacationForm = new VacationForm();

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }

  sendVacationForm() {
    this.httpService.vacationForm(this.vacation);
  }
}
