import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { User } from '../models/User';

@Component({
  selector: 'app-fire-someone',
  templateUrl: './fire-someone.component.html',
  styleUrls: ['./fire-someone.component.css']
})
export class FireSomeoneComponent implements OnInit {
  user: User = new User();
  usersList: Array<User>;
  reason;
  visible = false;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getUsers().then((result) => {
      this.usersList = result;
    });
  }
  leaveSomeone() {
    this.httpService.fireUser(this.reason, this.user);
    this.visible = true;
  }
}
