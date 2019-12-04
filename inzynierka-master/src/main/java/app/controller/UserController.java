package app.controller;

import javax.validation.Valid;

import app.model.Employee;
import app.model.StringResponse;
import app.model.Task;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editPassword(@RequestBody String password, @PathVariable Long id) {
        userService.changePassword(password, id);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public long getIdUser() {
        return userService.getId();
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getUsers();
    }


    @RequestMapping(path = "/userRole",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StringResponse getUserRole() {
        return userService.getUserRole();
    }

    @RequestMapping(path = "/task",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTask(){
        return      userService.getUserTask();
    }


}

