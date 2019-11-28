import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-menu-after-login-accountant',
  templateUrl: './menu-after-login-accountant.component.html',
  styleUrls: ['./menu-after-login-accountant.component.css']
})
export class MenuAfterLoginAccountantComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit() {
  }

}
