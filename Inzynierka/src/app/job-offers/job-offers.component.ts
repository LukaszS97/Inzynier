import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { BehaviorSubject } from 'rxjs';
import { Joboffer } from '../models/joboffer';

@Component({
  selector: 'app-job-offers',
  templateUrl: './job-offers.component.html',
  styleUrls: ['./job-offers.component.css']
})
export class JobOffersComponent implements OnInit {
  jobsListObs = new BehaviorSubject<Array<Joboffer>>([]);

  constructor(private httpService: HttpService) {
    this.httpService.getJoboffers().subscribe(list => {
    this.jobsListObs.next(list);
    console.log(this.jobsListObs);
    });
  }

  ngOnInit() {
  }
}
