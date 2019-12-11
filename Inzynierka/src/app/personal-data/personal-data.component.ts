import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Employee } from '../models/employee';
import { Router } from '@angular/router';

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
export class PersonalDataComponent implements OnInit {
  employee: Employee = new Employee();
  visible = false;
  button = 'Pokaż';
  result: Employee = new Employee();
  userRole;
  constructor(private httpService: HttpService, private router: Router) { }


  ngOnInit() {
    this.httpService.getId().then((userId) => {
      console.log(userId);
      this.httpService.getEmployee(userId).then((person) => {
        this.result = person;
        console.log(person);
      });
    });

  }

  putEmployee() {
    this.httpService.getId().then((userId) => {
      const result = this.httpService.putEmployee(userId, this.employee);
      if (result) {
        this.button = 'Pokaż';
        this.visible = false;
      }
    });
  }

  showHideForm() {
    this.visible = !this.visible;
    if (this.visible === true) {
      this.button = 'Ukryj';
    } else { this.button = 'Pokaż'; }
  }
}
