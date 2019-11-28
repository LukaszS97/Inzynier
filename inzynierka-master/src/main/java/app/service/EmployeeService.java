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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userRepository.findByEmail(name);
        //user.setUserRole(userRoleRepository.findByRole("ROLE_EMPLOYEE"));
        employee.setUser(user);
        employeeRepository.save(employee);
    }

    public void addNullEmployee(User user) {
        Employee employee = new Employee();
        employee.setUser(user);
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
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found"));
        employee.setUser(null);
        return employee;
    }

    public void removeEmployee(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found"));
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found"));
        user.setEmployee(null);
        employee.setUser(null);
        userRepository.delete(user);
        employeeRepository.delete(employee);

    }

}
