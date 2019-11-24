import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Joboffer } from '../models/joboffer';
import { Router } from '@angular/router';

@Component({
  selector: 'app-job-offers',
  templateUrl: './job-offers.component.html',
  styleUrls: ['./job-offers.component.css']
})
export class JobOffersComponent implements OnInit {
  jobsList: Array<Joboffer>;
  submit = true;
  constructor(private httpService: HttpService, private router: Router) {

  }

  ngOnInit() {
    this.httpService.getJoboffers().then((list: Array<Joboffer>) => {
      this.jobsList = list;
    });
  }

  showDetails() {
    this.submit = false;
  }
  hideDetails() {
    this.submit = true;
  }
  aplication() {
    this.router.navigate(['candidate']);
  }
}
