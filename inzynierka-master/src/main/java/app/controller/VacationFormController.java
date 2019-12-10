package app.controller;

import app.model.TaskReport;
import app.model.VacationForm;
import app.service.VacationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vacationForm")
@CrossOrigin
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

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VacationForm> getVacationForm(){
        return vacationFormService.getVacationForms();
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putVacationForm(@RequestBody VacationForm vacationForm){
        vacationFormService.refreshVacationForms(vacationForm);
    }


}
