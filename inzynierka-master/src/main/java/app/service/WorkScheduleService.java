package app.service;

import app.model.Employee;
import app.model.User;
import app.model.WorkSchedule;
import app.repository.EmployeeRepository;
import app.repository.UserRepository;
import app.repository.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WorkScheduleService {

    private WorkScheduleRepository workScheduleRepository;
    private UserRepository userRepository;

    @Autowired
    public void setWorkScheduleRepository(WorkScheduleRepository workScheduleRepository) {
        this.workScheduleRepository = workScheduleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
