import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpService } from './http.service';
import { Employee } from './models/employee';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Inzynierka';
  public userRole: string;
  public result: Employee;
  constructor(public authService: AuthService, public httpService: HttpService) {

  }

  ngOnInit() {
    this.httpService.getUserRole().then((userRole) => {
      this.userRole = userRole.response;
    });
    this.httpService.getId().then((userId) => {
      this.httpService.getEmployee(userId).then((person) => {
        this.result = person;
        console.log(this.result);
      });
    });
  }
}


