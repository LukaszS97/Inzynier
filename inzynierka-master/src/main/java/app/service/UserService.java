package app.service;

import app.model.*;
import app.repository.EmployeeRepository;
import app.repository.UserRepository;
import app.repository.UserRoleRepository;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private MailService mailService;


    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }




    /*
    public void addWithDefaultRole(User user) {
        UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
        user.setUserRole(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        Date date = new Date();
        user.setRegistrationDate(date);
        userRepository.save(user);
    }

 */

    public void addUser(User user, String role) {
        UserRole userRole = userRoleRepository.findByRole(role);
        user.setUserRole(userRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        Date date = new Date();
        user.setRegistrationDate(date);
        userRepository.save(user);
        employeeService.addNullEmployee(user);

    }

    public void changePassword(String password, Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found"));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }


    public long getId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        return userRepository.findByEmail(email).getId_user();
    }


    public StringResponse getUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        StringResponse stringResponse = new StringResponse(userRepository.findByEmail(email).getUserRole().getRole());
        return stringResponse;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<Task> getUserTask() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User user = userRepository.findByEmail(name);

        List<Task> list = user.getTasks();

        list = list.stream()
                .filter(x -> x.isDone() == false)
                .collect(Collectors.toList());

        return list;
    }

    public void removeUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User user = userRepository.findByEmail(name);
        Employee employee = employeeRepository.findById(user.getId_user()).orElseThrow(
                () -> new NoSuchElementException("Not found"));
        employeeRepository.delete(employee);
        userRepository.delete(user);

    }

    public void removeIndicatedUser(User user, String reson) throws MessagingException {
        User usr = userRepository.findByEmail(user.getEmail());
        Employee employee = employeeRepository.findById(usr.getId_user()).orElseThrow(
                () -> new NoSuchElementException("Not found"));

        Mail mail = new Mail();
        mail.setTo(user.getEmail());
        mail.setSubject("Zwolnienie");
        mail.setText(reson);
        mailService.sendMail(mail, true);

        employeeRepository.delete(employee);
        userRepository.delete(usr);
    }
}

