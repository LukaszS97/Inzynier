import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { User } from '../models/User';
import { Employee } from '../models/employee';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();


  constructor(private httpService: HttpService,
    private authSerive: AuthService, private router: Router) { }

  ngOnInit() {
  }
  login() {
    this.httpService.login(this.user).subscribe(token => {
    this.authSerive.setApiToken(token);
    this.router.navigate(['personal-data']);
    console.log(token);
    });
  }
}

