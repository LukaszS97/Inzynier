import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Joboffer } from '../models/joboffer';

@Component({
  selector: 'app-add-job-offer',
  templateUrl: './add-job-offer.component.html',
  styleUrls: ['./add-job-offer.component.css']
})
export class AddJobOfferComponent implements OnInit {
  joboffer: Joboffer = new Joboffer();
  submitted = false;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }
  async addJoboffer() {
    const result = this.httpService.addJoboffer(this.joboffer);
    if (result) {
      this.submitted = true;
    }
}
}
