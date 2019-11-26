import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Employee } from '../models/employee';
import { User } from '../models/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
export class PersonalDataComponent implements OnInit {
  employee: Employee = new Employee();
  visible = false;
  visible1 = false;
  button = 'Pokaż';
  result: Employee;
  constructor(private httpService: HttpService, private router: Router) { }


  ngOnInit() {
    this.httpService.getId().then((userId) => {

      this.httpService.getEmployee(userId).then((person) => {
        this.result = person;
        if (this.result) {
          this.visible1 = true;
        }
      });
    });
  }

  createEmployee() {
    const result = this.httpService.createEmployee(this.employee);
    if (result) {
      this.button = 'Pokaż';
      this.visible = false;
    }

  }
  showHideForm() {
    this.visible = !this.visible;
    if (this.visible === true) {
      this.button = 'Ukryj';
    } else { this.button = 'Pokaż'; }
  }
}
