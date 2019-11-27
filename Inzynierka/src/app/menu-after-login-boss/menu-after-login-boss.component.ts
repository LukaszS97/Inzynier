import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-menu-after-login-boss',
  templateUrl: './menu-after-login-boss.component.html',
  styleUrls: ['./menu-after-login-boss.component.css']
})
export class MenuAfterLoginBossComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit() {
  }

}
