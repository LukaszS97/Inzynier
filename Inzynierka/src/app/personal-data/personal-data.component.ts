import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Employee } from '../models/employee';
import { User } from '../models/User';

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
export class PersonalDataComponent implements OnInit {
  employee: Employee = new Employee();
  visible = false;
  button = 'Pokaż';
  result: Employee;
  constructor(private httpService: HttpService) { }


  ngOnInit() {
     this.httpService.getId().then((userId) => {

       this.httpService.getEmployee(userId).then((person) => {
         console.log('sdcwcwc');
         this.result = person;
      //   console.log(person);
       }).catch((error) => {
         console.log(error);
       });
     });
     console.log(this.result);
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
