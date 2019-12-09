import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Task } from '../models/task';
import { Raport } from '../models/raport';

@Component({
  selector: 'app-excercise',
  templateUrl: './excercise.component.html',
  styleUrls: ['./excercise.component.css']
})
export class ExcerciseComponent implements OnInit {

  task: Array<Task>;
  raport: Raport = new Raport();
  submitted = false;
  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getTask().then((result) => {
      this.task = result;
    });
  }

  addRaport() {
    this.httpService.addRaport(this.raport);
    this.submitted = true;
  }
  showAddRaport() {
    this.submitted = false;
  }
}
