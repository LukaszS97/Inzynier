package app.service;

import app.model.Employee;
import app.model.User;
import app.model.WorkSchedule;
import app.repository.EmployeeRepository;
import app.repository.UserRepository;
import app.repository.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WorkScheduleService {

    private WorkScheduleRepository workScheduleRepository;
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public void setWorkScheduleRepository(WorkScheduleRepository workScheduleRepository) {
        this.workScheduleRepository = workScheduleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public void saveWorkSchedule(WorkSchedule workSchedule, String employeeName) {
        User user = userRepository.findByEmail(employeeName);
        Employee employee = user.getEmployee();

        workSchedule.setEmployee(employee);
        workScheduleRepository.save(workSchedule);
    }


    public List<WorkSchedule> showAllWorkSchedule() {
        return workScheduleRepository.findAll();
    }

    public List<WorkSchedule> showWorkScheduleForEmployee() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User user = userRepository.findByEmail(name);

        Employee employee = employeeRepository.findById(user.getId_user())
                .orElseThrow(() -> new NoSuchElementException("Not found"));

        return employee.getWorkSchedule();

    }

    public void refreshWorkSchedule(Employee employee, long idWorkSchedule) {
        WorkSchedule workSchedule = workScheduleRepository.findById(idWorkSchedule).orElseThrow(() ->
                new NoSuchElementException("Not found"));
        employee = employeeRepository.findById(employee.getId()).orElseThrow(() ->
                new NoSuchElementException("Not found"));


        workSchedule.setEmployee(employee);
        workScheduleRepository.save(workSchedule);
    }
}

