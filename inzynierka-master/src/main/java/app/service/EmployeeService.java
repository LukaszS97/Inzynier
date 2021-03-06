package app.service;

import app.model.Employee;
import app.model.User;
import app.model.WorkSchedule;
import app.repository.EmployeeRepository;
import app.repository.UserRepository;
import app.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        if (employee.getFirstName() != null)
            employ.setFirstName(employee.getFirstName());
        if (employee.getLastName() != null)
            employ.setLastName(employee.getLastName());
        if (employee.getPhoneNumber() != null)
            employ.setPhoneNumber(employee.getPhoneNumber());
        if (employee.getBankAccountNumber() != null)
            employ.setBankAccountNumber(employee.getBankAccountNumber());
        if (employee.getAddress() != null)
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
        employeeRepository.delete(employeeRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found")));

    }


    //Janusz edition
    public List<Employee> getEmployees(String date) {

        String[] strings = date.split("-");
        int year = Integer.valueOf(strings[0]);
        int day = Integer.valueOf(strings[1]);
        int month = Integer.valueOf(strings[2]);
        //UWAGA!!!!!!
        //W LocalDate.of() zamieniona kolejnosc miesiaca z dniem(w bazie sie inny format zapisywal)
        LocalDate ldate = LocalDate.of(year, day, month);

        List<Employee> list = employeeRepository.findAll();
        List<Employee> returnList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getWorkSchedule().size(); j++) {
                if (list.get(i).getWorkSchedule().get(j).getLocalDate().equals(ldate)) {
                    Employee empl = list.get(i);
                    List<WorkSchedule> scheduleList = new ArrayList<>();
                    scheduleList.add(list.get(i).getWorkSchedule().get(j));
                    empl.setWorkSchedule(scheduleList);
                    returnList.add(empl);
                    break;
                }
            }
        }
        return returnList;




        /*
        List<Employee> list = employeeRepository.findAll();
        List<Employee> newList = new ArrayList<>();

        System.out.println("1111111111 " + list);
        System.out.println("1111111111newList " + newList);

        list.stream()
                .map(employee -> employee.getWorkSchedule().stream()
                        .filter(schedule -> (schedule.getLocalDate()).equals(LocalDate.of(year, month, day)))
                        .forEach(cos -> newList.add(cos)))
                .forEach(cos -> newList.add(cos));


        System.out.println("2222222222 " + list);
        System.out.println("2222222222newList " + newList);

        return list;

         */




/*
        List<Employee> list = employeeRepository.findAll();
        List<Employee> filtList = new ArrayList<>();

        for (int i = 0; list.size() > i; i++) {
            list.forEach(employee -> {
                List<WorkSchedule> schedule = employee.getWorkSchedule();
                System.out.println("1 " + schedule);
                for (int j = 0;schedule.size() >j; j++){

                }
                if (schedule.equals(ldate)) {
                    filtList.add(employee);
                    System.out.println("2 " + filtList);
                }
                System.out.println("3 " + filtList);
            });
            System.out.println("4 " + filtList);
        }
        System.out.println("5 " + filtList);
        System.out.println(list);
        System.out.println(filtList);
        return filtList;



 */
    }


}
