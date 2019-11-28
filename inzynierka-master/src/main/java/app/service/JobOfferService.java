package app.service;

import app.model.JobOffer;
import app.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class JobOfferService {

    private JobOfferRepository jobOfferRepository;

    @Autowired
    public void setJobOfferRepository(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }


    public List<JobOffer> showJobOffers() {
        return jobOfferRepository.findAll();
    }

    public JobOffer showJobOffer(Long id) {
        return jobOfferRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found"));
    }

    public JobOffer getJobOffer(String position) {
        return jobOfferRepository.findByPosition(position);
    }

    public void saveJobOffer(JobOffer jobOffer) {
        jobOfferRepository.save(jobOffer);
    }

    public void updateJobOffer(JobOffer jobOffer, Long id) {
        JobOffer oldJobOffer = jobOfferRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found"));
        oldJobOffer.setPosition(jobOffer.getPosition());
        oldJobOffer.setJobDescription(jobOffer.getJobDescription());
        oldJobOffer.setRequirements(jobOffer.getRequirements());
        oldJobOffer.setNiceToHave(jobOffer.getNiceToHave());
        oldJobOffer.setConditions(jobOffer.getConditions());

        jobOfferRepository.save(oldJobOffer);
    }

    public void deleteJobOffer(Long id) {
        JobOffer jobOffer = jobOfferRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("Not found"));
        jobOfferRepository.delete(jobOffer);
    }
}

