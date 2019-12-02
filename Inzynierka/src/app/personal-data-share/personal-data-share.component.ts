import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { Employee } from '../models/employee';
import { IfStmt } from '@angular/compiler';

@Component({
  selector: 'app-personal-data-share',
  templateUrl: './personal-data-share.component.html',
  styleUrls: ['./personal-data-share.component.css']
})
export class PersonalDataShareComponent implements OnInit {
  employee: Employee = new Employee();
  result: Employee;
  constructor(private httpService: HttpService, private router: Router) { }

  ngOnInit() {
    this.httpService.getId().then((userId) => {
      this.httpService.getEmployee(userId).then((person) => {
        this.result = person;
        if (this.result.lastName !== null) {
          this.router.navigate(['personal-data']);
        }
      });
    });
  }

  putEmployee() {
    this.httpService.getId().then((userId) => {
      const result = this.httpService.putEmployee(userId, this.employee);
      if (result) {
        this.router.navigate(['personal-data']);
      }
    });
  }
}
