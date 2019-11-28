import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { CandidateEmployee } from '../models/candidate-employee';

@Component({
  selector: 'app-cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  cvList: Array<CandidateEmployee>;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getCV().then((list: Array<CandidateEmployee>) => {
      this.cvList = list;
    });
  }

}
