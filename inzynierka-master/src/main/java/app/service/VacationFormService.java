package app.service;

import app.model.Employee;
import app.model.User;
import app.model.VacationForm;
import app.model.WorkSchedule;
import app.repository.EmployeeRepository;
import app.repository.UserRepository;
import app.repository.VacationFormRepository;
import app.repository.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class VacationFormService {

    private VacationFormRepository vacationFormRepository;
    private WorkScheduleService workScheduleService;
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private WorkScheduleRepository workScheduleRepository;

    @Autowired
    public void setVacationFormRepository(VacationFormRepository vacationFormRepository) {
        this.vacationFormRepository = vacationFormRepository;
    }

    @Autowired
    public void setWorkScheduleService(WorkScheduleService workScheduleService) {
        this.workScheduleService = workScheduleService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setWorkScheduleRepository(WorkScheduleRepository workScheduleRepository) {
        this.workScheduleRepository = workScheduleRepository;
    }


    public void saveVacationForm(VacationForm vacationForm) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User user = userRepository.findByEmail(name);
        Employee employee = employeeRepository.findById(user.getId_user()).orElseThrow(
                () -> new NoSuchElementException("Not found"));

        vacationForm.setEmployee(employee);
        vacationFormRepository.save(vacationForm);
    }

    public List<VacationForm> getVacationForms() {
        List<VacationForm> list = vacationFormRepository.findAll();

        list = list.stream()
                .filter(x -> x.isDone() == false)
                .collect(Collectors.toList());
        return list;
    }

    public void refreshVacationForms(VacationForm vacationForm) {
        long idEmployee = vacationForm.getEmployee().getId();

        Employee employee = employeeRepository.findById(idEmployee).orElseThrow(
                () -> new NoSuchElementException("Not found"));

        List<WorkSchedule> list = employee.getWorkSchedule();

        //tylko daty przed rozpoczeciem i po zakonczeniu urlopu
        if (vacationForm.isAccepted() == true) {
            list = list.stream()
                    .filter(x -> ((x.getLocalDate().isAfter(vacationForm.getEndDate())) &&
                            (x.getLocalDate().isBefore(vacationForm.getStartDate()))))
                    .collect(Collectors.toList());

            workScheduleRepository.deleteAll();
            employee.setWorkSchedule(list);
            employeeRepository.save(employee);
            vacationForm.setDone(true);
            vacationFormRepository.save(vacationForm);
        }else{
            vacationForm.setDone(true);
        }
    }
}



        /*
        List<WorkSchedule> list = employee.getWorkSchedule();

        //tylko daty przed rozpoczeciem i po zakonczeniu urlopu
        list = list.stream()
                .filter(x -> ((x.getLocalDate().isAfter(vacationForm.getEndDate())) &&
                        (x.getLocalDate().isBefore(vacationForm.getStartDate()))))
                .collect(Collectors.toList());


        employee.setWorkSchedule(list);
        employeeRepository.save(employee);
*/
