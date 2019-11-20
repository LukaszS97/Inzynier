import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-job-offers',
  templateUrl: './job-offers.component.html',
  styleUrls: ['./job-offers.component.css']
})
export class JobOffersComponent implements OnInit {

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }
  getJoboffers() {
    this.httpService.getJoboffers().subscribe(offers => {
      console.log(offers);
    });
  }
}
