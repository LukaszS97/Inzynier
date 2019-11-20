import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Employee } from '../models/employee';

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
export class PersonalDataComponent implements OnInit {

  employee: Employee = new Employee();
  visible = false;
  button = 'Pokaż';

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }

  createEmployee() {
    this.httpService.createEmployee(this.employee).subscribe(data => {
      this.button = 'Pokaż';
      this.visible = false;
    });
  }
  showHideForm() {
    this.visible = !this.visible;
    if (this.visible === true) {
      this.button = 'Ukryj';
    } else { this.button = 'Pokaż'; }
  }
}
