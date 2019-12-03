import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { CandidateEmployee } from '../models/candidate-employee';
import { Email } from '../models/email';

@Component({
  selector: 'app-cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  cvList: Array<CandidateEmployee>;
  submit = true;
  submitted = false;
  email: Email = new Email();

  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getCV().then((list: Array<CandidateEmployee>) => {
      this.cvList = list;
    });
  }

  showEmail() {
    this.submit = false;
  }
  hideEmail() {
    this.submit = true;
  }
  sendEmail() {
    this.httpService.sendEmail(this.email);
    this.submitted = true;
  }
}
