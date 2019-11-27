package app.service;

import app.model.Employee;
import app.model.User;
import app.model.UserRole;
import app.repository.EmployeeRepository;
import app.repository.UserRepository;
import app.repository.UserRoleRepository;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;


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
    }

    public void changePassword(String password, Long id){
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


    public String getUserRole(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found"));
        return user.getUserRole().getRole();
    }
}

