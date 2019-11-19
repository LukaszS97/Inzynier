import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { User } from '../models/User';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: User = new User();
  submitted = false;
  com = '';
  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }
  addUser() {
    this.httpService.addUser(this.user).subscribe(data => {
      if (data === 'Created') {
        this.submitted = true;
      }
    });
  }
}


