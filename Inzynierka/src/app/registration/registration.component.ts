import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { User } from '../models/User';
import { UserRole } from '../models/user-role';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: User = new User();
  role: UserRole = new UserRole();
  submitted = false;
  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }
  addUser() {
    const result = this.httpService.addUser(this.user, this.role);
    if (result) {
      this.submitted = true;
    }
  }
}


