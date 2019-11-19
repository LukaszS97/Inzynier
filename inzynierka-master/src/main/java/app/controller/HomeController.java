package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@CrossOrigin
public class HomeController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            userService.addWithDefaultRole(user);
            return ResponseEntity.ok("Created");
    }else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not created");
}
/*
@GetMapping(value = "/")
    public String home(){
        return "hello";
}

    @GetMapping(value = "/private")
    public String privateArea(){
        return "private";
    }
*/



}
