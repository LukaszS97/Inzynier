import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { User } from '../models/User';
import { LoginResult } from '../models/login-result';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();
  id: Promise<any>;
  constructor(private httpService: HttpService, private authSerive: AuthService, private router: Router) { }

  ngOnInit() {
  }

  async login() {
    const result: LoginResult = await this.httpService.login(this.user);
    if (result) {
      this.authSerive.setApiToken(result);
      this.router.navigate(['personal-data']);
      this.id = this.httpService.getId();
      console.log(this.id);
    }
  }
}

