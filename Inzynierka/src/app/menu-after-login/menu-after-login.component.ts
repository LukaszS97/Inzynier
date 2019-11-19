import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-menu-after-login',
  templateUrl: './menu-after-login.component.html',
  styleUrls: ['./menu-after-login.component.css']
})
export class MenuAfterLoginComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit() {
  }

}
