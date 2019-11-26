import { Component, OnInit } from '@angular/core';
import { CandidateEmployee } from '../models/candidate-employee';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { JobName } from '../models/job-name';
import { Joboffer } from '../models/joboffer';

@Component({
  selector: 'app-candidate',
  templateUrl: './candidate.component.html',
  styleUrls: ['./candidate.component.css']
})
export class CandidateComponent implements OnInit {
  candidate: CandidateEmployee = new CandidateEmployee();
  submitted = false;
  job: JobName = new JobName();
  jobsList: Array<Joboffer>;

  constructor(private httpService: HttpService, private router: Router) { }

  ngOnInit() {
    this.httpService.getJoboffers().then((list: Array<Joboffer>) => {
      this.jobsList = list;
    });
  }
  addCandidate() {
    const result = this.httpService.addCandidate(this.candidate, this.job);
    if (result) {
      this.submitted = true;
  }
}
  jobOffers(){
    this.router.navigate(['job-offers']);
  }
}
