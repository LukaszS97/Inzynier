import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-menu-after-login-employee',
  templateUrl: './menu-after-login-employee.component.html',
  styleUrls: ['./menu-after-login-employee.component.css']
})
export class MenuAfterLoginEmployeeComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit() {
  }

}
