import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-menu-after-login-manager',
  templateUrl: './menu-after-login-manager.component.html',
  styleUrls: ['./menu-after-login-manager.component.css']
})
export class MenuAfterLoginManagerComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit() {
  }

}
