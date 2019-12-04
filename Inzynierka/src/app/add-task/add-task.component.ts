import { Component, OnInit } from '@angular/core';
import { Task } from '../models/task';
import { HttpService } from '../http.service';
import { User } from '../models/User';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {
  addTask: Task = new Task();
  usersList: Array<User>;
  tasks: Array<Task>;
  submitted = false;
  napis = 'Pokaż';
  pozwolenie = false;
  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getUsers().then((result) => {
      this.usersList = result;
    });
  }

  addTasks() {
    this.httpService.addTasks(this.addTask);
    this.submitted = true;
    this.pozwolenie = false;
  }

  showTasks() {
    this.httpService.getTasks().then((task) => {
      this.tasks = task;
    });
    this.pozwolenie = !this.pozwolenie;
    if (this.pozwolenie === true) {
      this.napis = 'Ukryj';
    } else {
      this.napis = 'Pokaż';
    }
  }
}
