package app.controller;

import app.model.Employee;
import app.model.WorkSchedule;
import app.service.WorkScheduleService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/workSchedule")
public class WorkScheduleController {

    private WorkScheduleService workScheduleService;

    @Autowired
    public void setWorkScheduleService(WorkScheduleService workScheduleService) {
        this.workScheduleService = workScheduleService;
    }

    @RequestMapping(path = "/{employeeName}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postWorkSchedule(@RequestBody WorkSchedule workSchedule, @PathVariable String employeeName) {
        workScheduleService.saveWorkSchedule(workSchedule, employeeName);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkSchedule> getAllWorkSchedule() {
        return workScheduleService.showAllWorkSchedule();
    }

}
