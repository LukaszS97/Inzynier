package app.controller;

import app.model.JobOffer;
import app.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/joboffer")
public class JobOfferController {

    private JobOfferService jobOfferService;

    @Autowired
    public void setJobOfferService(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobOffer> getJobOffers() {
        return jobOfferService.showJobOffers();
    }

    @RequestMapping(path = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public JobOffer getJobOffer(@PathVariable Long id) {
        return jobOfferService.showJobOffer(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postJobOffer(@RequestBody JobOffer jobOffer, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            jobOfferService.saveJobOffer(jobOffer);
            return ResponseEntity.ok("created");
        } else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not created");
    }

    @RequestMapping(path = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putJobOffer(@RequestBody JobOffer jobOffer, @PathVariable Long id, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            jobOfferService.updateJobOffer(jobOffer, id);
            return ResponseEntity.ok("updated");
        } else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not updated");
    }


    @RequestMapping(path = "/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteJobOffer(@PathVariable Long id) {
        jobOfferService.deleteJobOffer(id);
    }
}
