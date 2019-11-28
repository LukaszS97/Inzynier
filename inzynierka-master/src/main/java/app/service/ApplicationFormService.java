package app.service;

import app.model.ApplicationForm;
import app.model.JobOffer;
import app.repository.ApplicationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApplicationFormService {

    private ApplicationFormRepository applicationFormRepository;
    private JobOfferService jobOfferService;

    @Autowired
    public void setApplicationFormRepository(ApplicationFormRepository applicationFormRepository) {
        this.applicationFormRepository = applicationFormRepository;
    }

    @Autowired
    public void setJobOfferService(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    public void saveApplicationForm(ApplicationForm applicationForm, String position) {
        JobOffer jobOffer = jobOfferService.getJobOffer(position);
        applicationForm.setJobOffer(jobOffer);
        applicationFormRepository.save(applicationForm);
    }
}
