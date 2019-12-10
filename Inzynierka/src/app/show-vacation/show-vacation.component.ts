import { Component, OnInit } from '@angular/core';
import { VacationForm } from '../models/vacation-form';

@Component({
  selector: 'app-show-vacation',
  templateUrl: './show-vacation.component.html',
  styleUrls: ['./show-vacation.component.css']
})
export class ShowVacationComponent implements OnInit {
  vacationFormList: Array<VacationForm>;

  constructor() { }

  ngOnInit() {
  }

  showVacationForm() {

  }
}
