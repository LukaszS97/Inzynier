import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-menu-after-login-human-resources',
  templateUrl: './menu-after-login-human-resources.component.html',
  styleUrls: ['./menu-after-login-human-resources.component.css']
})
export class MenuAfterLoginHumanResourcesComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit() {
  }

}
