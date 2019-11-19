package app.service;

import app.model.Employee;
import app.model.User;
import app.repository.EmployeeRepository;
import app.repository.UserRepository;
import app.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    public void completeDataAfterFirstLogin(Employee employee) {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String name = auth.getName(); //get logged in username
         //User user = userRepository.findById((long) 1).orElseThrow(() ->
         //new NoSuchElementException("Not found"));
         System.out.println("300000000000000000000!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         User user = userRepository.findByEmail("w@w.w");
         System.out.println("400000000000000000000!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         user.setUserRole(userRoleRepository.findByRole("ROLE_EMPLOYEE"));
         System.out.println("500000000000000000000!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         employee.setUser(user);
         System.out.println("600000000000000000000!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         //employee.setUser(userRepository.findByEmail("a@a.a"));
         employeeRepository.save(employee);
     }


    public void editEmployeeData(Employee employee, Long id) {
        Employee employ = employeeRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found"));
        employ.setFirstName(employee.getFirstName());
        employ.setLastName(employee.getLastName());
        employ.setPhoneNumber(employee.getPhoneNumber());
        employ.setBankAccountNumber(employee.getBankAccountNumber());
        employ.setAddress(employee.getAddress());
        employeeRepository.save(employ);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found"));
    }

    public void removeEmployee(Long id) {
        employeeRepository.delete(employeeRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found")));
    }

}
