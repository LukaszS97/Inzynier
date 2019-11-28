import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpService } from './http.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Inzynierka';

  public userRole: string;

  constructor(public authService: AuthService, public httpService: HttpService) {
    this.httpService.getUserRole().then((userRole) => {
      this.userRole = userRole.response;
    });
  }
}


