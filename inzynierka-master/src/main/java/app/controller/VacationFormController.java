package app.controller;

import app.model.TaskReport;
import app.model.VacationForm;
import app.service.VacationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vacationForm")
public class VacationFormController {

    private VacationFormService vacationFormService;

    @Autowired
    public void setVacationFormService(VacationFormService vacationFormService) {
        this.vacationFormService = vacationFormService;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postVacationForm(@RequestBody VacationForm vacationForm){
        vacationFormService.saveVacationForm(vacationForm);
    }


}
