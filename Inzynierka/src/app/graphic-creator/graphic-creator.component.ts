import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { HttpService } from '../http.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { Graphic } from '../models/graphic';

@Component({
  selector: 'app-graphic-creator',
  templateUrl: './graphic-creator.component.html',
  styleUrls: ['./graphic-creator.component.css']
})
export class GraphicCreatorComponent implements OnInit {

  graphicList: any = [];
  usersList: Array<User>;
  user: User;
  grafik: Graphic = new Graphic();
  constructor(private httpService: HttpService, private authSerive: AuthService, private router: Router) { }

  ngOnInit() {
    this.httpService.getUsers().then((result) => {
      this.usersList = result;
    });
  }

  addToList() {
    this.graphicList.push(this.grafik);
    console.log(this.graphicList);
  }

}
