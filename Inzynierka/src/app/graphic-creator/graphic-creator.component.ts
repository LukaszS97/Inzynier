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

  usersList: Array<User>;
  grafik: Graphic = new Graphic();
  grafikList: Array<Graphic>;
  email;
  submitted = false;
  napis = 'Pokaż';
  pozwolenie = false;
  data: Date;
  constructor(private httpService: HttpService, private authSerive: AuthService, private router: Router) { }

  ngOnInit() {
    this.httpService.getUsers().then((result) => {
      this.usersList = result;
    });
  }

  addGraphic() {
    const result = this.httpService.addGraphic(this.grafik, this.email);
    if (result) {
      this.submitted = true;
      this.pozwolenie = false;
    }
  }
  showGraphic() {
    this.httpService.getGraphicList(this.data).then((result) => {
      this.grafikList = result;
    });
    this.pozwolenie = !this.pozwolenie;
    if (this.pozwolenie === true) {
      this.napis = 'Ukryj';
    } else {
      this.napis = 'Pokaż';
    }

  }
}
