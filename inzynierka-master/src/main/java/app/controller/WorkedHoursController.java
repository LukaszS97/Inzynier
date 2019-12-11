package app.controller;

import app.model.WorkedHours;
import app.service.WorkedHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/workedHours")
@CrossOrigin
public class WorkedHoursController {

    private WorkedHoursService workedHoursService;

    @Autowired
    public void setWorkedHoursService(WorkedHoursService workedHoursService) {
        this.workedHoursService = workedHoursService;
    }


    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postWorkedHours(@RequestBody WorkedHours workedHours) {
        workedHoursService.saveWorkedHours(workedHours);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkedHours> getWorkedHours() {
        return workedHoursService.showWorkedHours();
    }

    @RequestMapping(path = "/users/{month}/{year}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkedHours> getWorkedHoursForAllUsers(@PathVariable int month,@PathVariable int year) {
        return workedHoursService.showWorkedHoursForAllUsers(month,year);
    }


}
