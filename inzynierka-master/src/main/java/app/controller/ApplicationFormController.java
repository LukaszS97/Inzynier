package app.controller;

import app.model.ApplicationForm;
import app.service.ApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/applicationForm")
public class ApplicationFormController {

    private ApplicationFormService applicationFormService;

    @Autowired
    public void setApplicationFormService(ApplicationFormService applicationFormService) {
        this.applicationFormService = applicationFormService;
    }

    //trzeba pozmieniac to id
    @RequestMapping(path = "/{position}",
            method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postApplicationForm(@RequestBody ApplicationForm applicationForm, @PathVariable String position, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            applicationFormService.saveApplicationForm(applicationForm, position);
            return ResponseEntity.ok("created");
        } else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not created");
    }

    


}
