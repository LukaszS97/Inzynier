package app.controller;

import app.model.Employee;
import app.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin
public class EmployeeController {

    private EmployeeService employeeService;
 
    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
/*
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveEmployeeData(@RequestBody Employee employee){
        employeeService.completeDataAfterFirstLogin(employee);
    }

 */

    @RequestMapping(path = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editData(@RequestBody @Valid Employee employee, @PathVariable Long id) {
        employeeService.editEmployeeData(employee, id);
    }

    @RequestMapping(path = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @RequestMapping(path = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEmployee(@PathVariable Long id){
        employeeService.removeEmployee(id);
    }
}
