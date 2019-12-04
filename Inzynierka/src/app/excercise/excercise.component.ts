import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Task } from '../models/task';

@Component({
  selector: 'app-excercise',
  templateUrl: './excercise.component.html',
  styleUrls: ['./excercise.component.css']
})
export class ExcerciseComponent implements OnInit {

  task: Array<Task>;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getTask().then((result) => {
      this.task = result;
    });
  }
}
