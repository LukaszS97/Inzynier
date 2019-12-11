package app.controller;

import app.model.Employee;
import app.model.WorkSchedule;
import app.service.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/workSchedule")
@CrossOrigin
public class WorkScheduleController {

    private WorkScheduleService workScheduleService;

    @Autowired
    public void setWorkScheduleService(WorkScheduleService workScheduleService) {
        this.workScheduleService = workScheduleService;
    }

    //wywal zmienna z urla bo dostaniesz w obiekcie workSchedule employa z samym mailem (dostaje pustego maila z mailem)
    @RequestMapping(path = "/{employeeName}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postWorkSchedule(@RequestBody WorkSchedule workSchedule, @PathVariable String employeeName) {
        workScheduleService.saveWorkSchedule(workSchedule, employeeName);
    }

    @RequestMapping(path = "/schedules",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkSchedule> getAllWorkSchedule() {
        return workScheduleService.showAllWorkSchedule();
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkSchedule> getWorkScheduleForEmployee() {
        return workScheduleService.showWorkScheduleForEmployee();
    }

    
    @RequestMapping(path = "/{idWorkSchedule}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putWorkSchedule(@RequestBody Employee employee, @PathVariable long idWorkSchedule) {
        workScheduleService.refreshWorkSchedule(employee, idWorkSchedule);
    }
}
