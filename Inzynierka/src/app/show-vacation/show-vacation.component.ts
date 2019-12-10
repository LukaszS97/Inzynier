import { Component, OnInit } from '@angular/core';
import { VacationForm } from '../models/vacation-form';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-show-vacation',
  templateUrl: './show-vacation.component.html',
  styleUrls: ['./show-vacation.component.css']
})
export class ShowVacationComponent implements OnInit {
  vacationFormList: Array<VacationForm>;
  vacationForm: VacationForm = new VacationForm();

  id;
  isAccepted;


  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getVacationFormList().then((result) => {
      this.vacationFormList = result;
    });
  }
  sendAnswer() {
    console.log(this.vacationForm);
  }

  // getDimensions(id) {
  //   return this.vacationFormList.find((x) => { x.id = id; });
  // }

  // sendAnswerr() {
  //   this.vacationForm = this.getDimensions(this.id);
  //   console.log(this.vacationForm);
  // }

}
