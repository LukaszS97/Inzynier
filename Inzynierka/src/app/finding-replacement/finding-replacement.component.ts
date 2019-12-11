import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { WorkSchedule } from '../models/work-schedule';
import { User } from '../models/User';

@Component({
  selector: 'app-finding-replacement',
  templateUrl: './finding-replacement.component.html',
  styleUrls: ['./finding-replacement.component.css']
})
export class FindingReplacementComponent implements OnInit {
  user: User = new User();
  users: Array<User>;
  graphicId;
  grafik: Array<WorkSchedule>;
  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getGraphic().then((result) => {
      this.grafik = result;
      console.log(this.grafik);
    });
    this.httpService.getUsers().then((result) => {
      this.users = result;
      console.log(this.users);
    });
  }

  putGraphic() {
    this.httpService.putGraphic(this.graphicId, this.user);
  }

}
