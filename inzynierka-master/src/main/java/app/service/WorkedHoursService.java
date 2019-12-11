package app.service;

import app.model.Employee;
import app.model.User;
import app.model.WorkSchedule;
import app.model.WorkedHours;
import app.repository.EmployeeRepository;
import app.repository.UserRepository;
import app.repository.WorkScheduleRepository;
import app.repository.WorkedHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkedHoursService {

    private WorkedHoursRepository workedHoursRepository;
    private WorkScheduleRepository workScheduleRepository;
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public void setWorkedHoursRepository(WorkedHoursRepository workedHoursRepository) {
        this.workedHoursRepository = workedHoursRepository;
    }

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

    public void saveWorkedHours(WorkedHours workedHours) {
        int month = workedHours.getMonth();
        int year = workedHours.getYear();

        List<WorkSchedule> workScheduleList = workScheduleRepository.findAll();
        List<User> userList = userRepository.findAll();

        for (int i = 0; i < userList.size(); i++) {
            //wyciagniecie z danego roku i miesiaca dla danego uzytkownika workScheduli
            int finalI = i;
            List<WorkSchedule> monthUserList = workScheduleList.stream()
                    .filter(schedule -> schedule.getEmployee().getUser().equals(userList.get(finalI)))
                    .filter(schedule -> schedule.getLocalDate().getMonthValue() == month)
                    .filter(schedule -> schedule.getLocalDate().getYear() == year)
                    .collect(Collectors.toList());


            int sum = 0;
            for (int j = 0; j < monthUserList.size(); j++) {
                sum += monthUserList.get(j).getEndTime() - monthUserList.get(j).getStartTime();
            }
            WorkedHours work = new WorkedHours();
            work.setHours(sum);
            work.setUser(userList.get(finalI));
            work.setMonth(month);
            work.setYear(year);
            workedHoursRepository.save(work);
        }

    }

    public List<WorkedHours> showWorkedHours() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        return workedHoursRepository.findAllByUser(userRepository.findByEmail(name));
    }

    public List<WorkedHours> showWorkedHoursForAllUsers(int month, int year) {
        List<WorkedHours> workedHoursList = workedHoursRepository.findAll();

        workedHoursList = workedHoursList.stream()
                .filter(hours -> ((hours.getMonth() == month)
                        && (hours.getYear() == year)))
                .collect(Collectors.toList());

        return workedHoursList;
    }
}


//    public List<WorkedHours> showWorkdeHours() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//
//        User user = userRepository.findByEmail(name);
//
//        List<WorkedHours> list = workedHoursRepository.findAll();
//
//        list = list.stream()
//                .filter(x -> x.getUser().getId_user() == user.getId_user())
//                .collect(Collectors.toList());
//
//        return list;
//    }
//
//
//
//    public void createWorkedHours(WorkedHours workedHours) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//        List<WorkSchedule> workScheduleList = workScheduleRepository.findAll();
//        List<WorkedHours> workedHoursList = workedHoursRepository.findAll();
//
//
//        //Jezeli nie ma wyniku dla tej daty bedzie pusta lista, jezeli jest to bedzie jedynym wynikiem w tablicy
//        workedHoursList = workedHoursList.stream()
//                .filter(x -> ((workedHours.getYear() == x.getYear())
//                        && (workedHours.getMonth() == x.getMonth())
//                        && (workedHours.getUser().equals(name))))
//                .collect(Collectors.toList());
//
//
//        LocalDate isNextDay = LocalDate.now().plusDays(1);
//        int hours = 0;
//
//        if (workedHours.getMonth() == isNextDay.getMonthValue()) {
//            for (WorkSchedule workSchedule : workScheduleList) {
//                if ((workSchedule.getLocalDate().getMonthValue() == workedHours.getMonth())
//                        && workSchedule.getLocalDate().getYear() == workedHours.getYear()
//                        && workSchedule.getEmployee().getUser().getEmail() == name) {
//                    hours += workSchedule.getEndTime() - workSchedule.getStartTime();
//                }
//            }
//            workedHours.setHours(hours);
//            workedHoursRepository.save(workedHours);
//        } else if (workedHoursList.isEmpty()) {
//            for (WorkSchedule workSchedule : workScheduleList) {
//                if ((workSchedule.getLocalDate().getMonthValue() == workedHours.getMonth())
//                        && workSchedule.getLocalDate().getYear() == workedHours.getYear()
//                        && workSchedule.getEmployee().getUser().getEmail() == name) {
//                    hours += workSchedule.getEndTime() - workSchedule.getStartTime();
//                }
//            }
//            workedHours.setHours(hours);
//            workedHoursRepository.save(workedHours);
//        }
//
//
//    }

