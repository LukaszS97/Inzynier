import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-leave',
  templateUrl: './leave.component.html',
  styleUrls: ['./leave.component.css']
})
export class LeaveComponent implements OnInit {

  constructor(private httpService: HttpService, private authSerive: AuthService, private router: Router) { }

  ngOnInit() {
  }

  leave() {
    this.httpService.getId().then((userId) => {
      const result = this.httpService.deleteEmployee(userId);
      if (result) {
        this.authSerive.signOut();
        this.router.navigate(['main']);
      }
    });
  }
}
